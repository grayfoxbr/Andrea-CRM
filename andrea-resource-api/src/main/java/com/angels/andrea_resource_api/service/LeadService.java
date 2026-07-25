package com.angels.andrea_resource_api.service;

import com.angels.andrea_resource_api.cache.ReactiveCacheService;
import com.angels.andrea_resource_api.dtos.LeadRequest;
import com.angels.andrea_resource_api.dtos.LeadResponse;
import com.angels.andrea_resource_api.dtos.LeadUpdateRequest;
import com.angels.andrea_resource_api.model.Lead;
import com.angels.andrea_resource_api.repo.LeadRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LeadService {

    private static final String CACHE_PREFIX = "lead:id:";
    private static final Duration TTL = Duration.ofMinutes(10);

    private final LeadRepository leadRepository;
    private final ReactiveCacheService cache;

    public LeadService(LeadRepository leadRepository,
                       ReactiveCacheService cache) {
        this.leadRepository = leadRepository;
        this.cache = cache;
    }

    public Flux<LeadResponse> findAll() {
        return leadRepository.findAll()
                .map(this::toResponse);
    }

    public Mono<LeadResponse> findById(Long id) {
        return cache.cacheable(
                        CACHE_PREFIX + id,
                        Lead.class,
                        TTL,
                        () -> leadRepository.findById(id))
                .map(this::toResponse);
    }

    public Mono<LeadResponse> findByLeadId(Long id) {
        return findById(id);
    }

    public Mono<Boolean> existsByLeadId(Long id) {
        return leadRepository.existsByLeadId(id);
    }

    public Mono<LeadResponse> save(LeadRequest request) {

        Lead entity = new Lead();

        entity.setCompanyId(request.companyId());
        entity.setIsLead(request.isLead());
        entity.setPhoneNumber(request.phoneNumber());
        entity.setEmail(request.email());
        entity.setDate(request.date());
        entity.setComunicationChannel(request.comunicationChannel());
        entity.setAgentName(request.agentName());
        entity.setLocation(request.location());
        entity.setOffer(request.offer());

        return leadRepository.save(entity)
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getLeadId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<LeadResponse> update(Long id,
                                     LeadUpdateRequest request) {

        return leadRepository.findById(id)
                .flatMap(entity -> {

                    entity.setCompanyId(request.companyId());
                    entity.setIsLead(request.isLead());
                    entity.setPhoneNumber(request.phoneNumber());
                    entity.setEmail(request.email());
                    entity.setDate(request.date());
                    entity.setComunicationChannel(request.comunicationChannel());
                    entity.setAgentName(request.agentName());
                    entity.setLocation(request.location());
                    entity.setOffer(request.offer());

                    return leadRepository.save(entity);
                })
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getLeadId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<Void> deleteById(Long id) {
        return leadRepository.deleteById(id)
                .then(cache.evict(CACHE_PREFIX + id))
                .then();
    }

    private LeadResponse toResponse(Lead entity) {
        return new LeadResponse(
                entity.getLeadId(),
                entity.getCompanyId(),
                entity.getIsLead(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getDate(),
                entity.getComunicationChannel(),
                entity.getAgentName(),
                entity.getLocation(),
                entity.getOffer()
        );
    }
}
