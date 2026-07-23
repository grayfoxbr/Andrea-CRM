package com.angels.andrea_resource_api.repo;

import com.angels.andrea_resource_api.model.Lead;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LeadRepository extends ReactiveCrudRepository<Lead, Long> {

    Mono<Lead> findByLeadId(Long leadId);

    Mono<Boolean> existsByLeadId(Long leadId);
}
