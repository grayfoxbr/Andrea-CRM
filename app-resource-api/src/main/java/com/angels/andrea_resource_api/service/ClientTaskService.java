package com.angels.andrea_resource_api.service;

import com.angels.andrea_resource_api.cache.ReactiveCacheService;
import com.angels.andrea_resource_api.dtos.ClientTaskRequest;
import com.angels.andrea_resource_api.dtos.ClientTaskResponse;
import com.angels.andrea_resource_api.dtos.ClientTaskUpdateRequest;
import com.angels.andrea_resource_api.model.ClientTask;
import com.angels.andrea_resource_api.repo.ClientTaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ClientTaskService {

    private static final String CACHE_PREFIX = "clienttask:id:";
    private static final Duration TTL = Duration.ofMinutes(10);

    private final ClientTaskRepository clientTaskRepository;
    private final ReactiveCacheService cache;

    public ClientTaskService(ClientTaskRepository clientTaskRepository,
                             ReactiveCacheService cache) {
        this.clientTaskRepository = clientTaskRepository;
        this.cache = cache;
    }

    public Flux<ClientTaskResponse> findAll() {
        return clientTaskRepository.findAll()
                .map(this::toResponse);
    }

    public Mono<ClientTaskResponse> findById(Long id) {
        return cache.cacheable(
                        CACHE_PREFIX + id,
                        ClientTask.class,
                        TTL,
                        () -> clientTaskRepository.findById(id))
                .map(this::toResponse);
    }

    public Flux<ClientTaskResponse> findByClientId(Long clientId) {
        return clientTaskRepository.findByClientId(clientId)
                .map(this::toResponse);
    }

    public Mono<ClientTaskResponse> save(ClientTaskRequest request) {

        ClientTask entity = new ClientTask();

        entity.setClientId(request.clientId());
        entity.setMeetingTopic(request.meetingTopic());
        entity.setTaskBegin(request.taskBegin());
        entity.setTaskEnd(request.taskEnd());
        entity.setProjectDescription(request.projectDescription());
        entity.setNotes(request.notes());

        return clientTaskRepository.save(entity)
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getTaskClientId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<ClientTaskResponse> update(Long id,
                                           ClientTaskUpdateRequest request) {

        return clientTaskRepository.findById(id)
                .flatMap(entity -> {

                    entity.setClientId(request.clientId());
                    entity.setMeetingTopic(request.meetingTopic());
                    entity.setTaskBegin(request.taskBegin());
                    entity.setTaskEnd(request.taskEnd());
                    entity.setProjectDescription(request.projectDescription());
                    entity.setNotes(request.notes());

                    return clientTaskRepository.save(entity);
                })
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getTaskClientId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<Void> deleteById(Long id) {
        return clientTaskRepository.deleteById(id)
                .then(cache.evict(CACHE_PREFIX + id))
                .then();
    }

    public Mono<Boolean> existsByTaskId(Long id) {
        return clientTaskRepository.existsByTaskClientId(id);
    }

    private ClientTaskResponse toResponse(ClientTask entity) {

        return new ClientTaskResponse(
                entity.getTaskClientId(),
                entity.getClientId(),
                entity.getMeetingTopic(),
                entity.getTaskBegin(),
                entity.getTaskEnd(),
                entity.getProjectDescription(),
                entity.getNotes()
        );
    }
}