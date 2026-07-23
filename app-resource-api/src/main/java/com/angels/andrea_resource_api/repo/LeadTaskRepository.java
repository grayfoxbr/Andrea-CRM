package com.angels.andrea_resource_api.repo;

import com.angels.andrea_resource_api.model.LeadTask;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LeadTaskRepository extends ReactiveCrudRepository<LeadTask, Long> {

    Mono<Boolean> existsByLeadTaskId(Long leadTaskId);

    Flux<LeadTask> findByLeadId(Long leadId);
}
