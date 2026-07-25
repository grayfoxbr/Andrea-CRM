package com.angels.andrea_resource_api.controller;

import com.angels.andrea_resource_api.dtos.ClientRequest;
import com.angels.andrea_resource_api.dtos.ClientResponse;
import com.angels.andrea_resource_api.dtos.ClientUpdateRequest;
import com.angels.andrea_resource_api.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public Flux<ClientResponse> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ClientResponse> getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientResponse> createClient(
            @RequestBody ClientRequest request) {

        return clientService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<ClientResponse> updateClient(
            @PathVariable Long id,
            @RequestBody ClientUpdateRequest request) {

        return clientService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteById(id);
    }
}