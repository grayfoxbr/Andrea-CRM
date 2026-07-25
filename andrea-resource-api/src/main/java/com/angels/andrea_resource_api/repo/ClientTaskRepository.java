package com.angels.andrea_resource_api.repo;

import com.angels.andrea_resource_api.model.ClientTask;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientTaskRepository extends ReactiveCrudRepository<ClientTask, Long> {

    Mono<Boolean> existsByTaskClientId(Long taskClientId);

    Flux<ClientTask> findByClientId(Long clientId);
}
