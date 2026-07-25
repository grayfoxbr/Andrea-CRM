package com.angels.andrea_resource_api.repo;

import com.angels.andrea_resource_api.model.ClientEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, Long> {

    Mono<Boolean> existsByClientId(Long clientId);
}
