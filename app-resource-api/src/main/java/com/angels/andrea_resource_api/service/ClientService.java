package com.angels.andrea_resource_api.service;

import com.angels.andrea_resource_api.cache.ReactiveCacheService;
import com.angels.andrea_resource_api.dtos.ClientRequest;
import com.angels.andrea_resource_api.dtos.ClientResponse;
import com.angels.andrea_resource_api.dtos.ClientUpdateRequest;
import com.angels.andrea_resource_api.model.ClientEntity;
import com.angels.andrea_resource_api.repo.ClientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ClientService {

    private static final String CACHE_PREFIX = "client:id:";
    private static final Duration TTL = Duration.ofMinutes(10);

    private final ClientRepository clientRepository;
    private final ReactiveCacheService cache;

    public ClientService(ClientRepository clientRepository,
                         ReactiveCacheService cache) {
        this.clientRepository = clientRepository;
        this.cache = cache;
    }

    public Flux<ClientResponse> findAll() {
        return clientRepository.findAll()
                .map(this::toResponse);
    }

    public Mono<ClientResponse> findById(Long id) {
        return cache.cacheable(
                        CACHE_PREFIX + id,
                        ClientEntity.class,
                        TTL,
                        () -> clientRepository.findById(id))
                .map(this::toResponse);
    }

    public Mono<ClientResponse> save(ClientRequest request) {

        ClientEntity entity = new ClientEntity();
        entity.setCompanyId(request.companyId());
        entity.setLeadId(request.leadId());

        return clientRepository.save(entity)
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getClientId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<ClientResponse> update(Long id,
                                       ClientUpdateRequest request) {

        return clientRepository.findById(id)
                .flatMap(entity -> {

                    entity.setCompanyId(request.companyId());
                    entity.setLeadId(request.leadId());

                    return clientRepository.save(entity);
                })
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getClientId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<Void> deleteById(Long id) {

        return clientRepository.deleteById(id)
                .then(cache.evict(CACHE_PREFIX + id))
                .then();
    }

    public Mono<Boolean> existsByClientId(Long id) {
        return clientRepository.existsByClientId(id);
    }

    private ClientResponse toResponse(ClientEntity entity) {

        return new ClientResponse(
                entity.getClientId(),
                entity.getCompanyId(),
                entity.getLeadId()
        );
    }
}