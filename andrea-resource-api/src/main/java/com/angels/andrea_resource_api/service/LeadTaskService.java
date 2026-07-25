package com.angels.andrea_resource_api.service;

import com.angels.andrea_resource_api.cache.ReactiveCacheService;
import com.angels.andrea_resource_api.dtos.LeadTaskRequest;
import com.angels.andrea_resource_api.dtos.LeadTaskResponse;
import com.angels.andrea_resource_api.dtos.LeadTaskUpdateRequest;
import com.angels.andrea_resource_api.model.LeadTask;
import com.angels.andrea_resource_api.repo.LeadTaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LeadTaskService {

    private static final String CACHE_PREFIX = "leadtask:id:";
    private static final Duration TTL = Duration.ofMinutes(10);

    private final LeadTaskRepository leadTaskRepository;
    private final ReactiveCacheService cache;

    public LeadTaskService(LeadTaskRepository leadTaskRepository,
                           ReactiveCacheService cache) {
        this.leadTaskRepository = leadTaskRepository;
        this.cache = cache;
    }

    public Flux<LeadTaskResponse> findAll() {
        return leadTaskRepository.findAll()
                .map(this::toResponse);
    }

    public Mono<LeadTaskResponse> findById(Long id) {
        return cache.cacheable(
                        CACHE_PREFIX + id,
                        LeadTask.class,
                        TTL,
                        () -> leadTaskRepository.findById(id))
                .map(this::toResponse);
    }

    public Flux<LeadTaskResponse> findByLeadId(Long leadId) {
        return leadTaskRepository.findByLeadId(leadId)
                .map(this::toResponse);
    }

    public Mono<LeadTaskResponse> save(LeadTaskRequest request) {

        LeadTask entity = new LeadTask();

        entity.setLeadId(request.leadId());
        entity.setAgentName(request.agentName());
        entity.setPlace(request.place());
        entity.setContactMethod(request.contactMethod());
        entity.setMeetingLocation(request.meetingLocation());
        entity.setProposal(request.proposal());
        entity.setFeedback(request.feedback());
        entity.setStatus(request.leadTaskStatus());

        return leadTaskRepository.save(entity)
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getLeadTaskId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<LeadTaskResponse> update(Long id,
                                         LeadTaskUpdateRequest request) {

        return leadTaskRepository.findById(id)
                .flatMap(entity -> {

                    entity.setLeadId(request.leadId());
                    entity.setAgentName(request.agentName());
                    entity.setPlace(request.place());
                    entity.setContactMethod(request.contactMethod());
                    entity.setMeetingLocation(request.meetingLocation());
                    entity.setProposal(request.proposal());
                    entity.setFeedback(request.feedback());
                    entity.setStatus(request.leadTaskStatus());

                    return leadTaskRepository.save(entity);
                })
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getLeadTaskId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<Void> deleteById(Long id) {
        return leadTaskRepository.deleteById(id)
                .then(cache.evict(CACHE_PREFIX + id))
                .then();
    }

    public Mono<Boolean> existsByLeadTaskId(Long id) {
        return leadTaskRepository.existsByLeadTaskId(id);
    }

    private LeadTaskResponse toResponse(LeadTask entity) {

        return new LeadTaskResponse(
                entity.getLeadTaskId(),
                entity.getLeadId(),
                entity.getAgentName(),
                entity.getPlace(),
                entity.getContactMethod(),
                entity.getMeetingLocation(),
                entity.getProposal(),
                entity.getFeedback(),
                entity.getStatus()
        );
    }
}