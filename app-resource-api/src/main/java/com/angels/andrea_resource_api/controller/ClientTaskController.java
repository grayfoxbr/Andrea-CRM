package com.angels.andrea_resource_api.controller;

import com.angels.andrea_resource_api.dtos.ClientTaskRequest;
import com.angels.andrea_resource_api.dtos.ClientTaskResponse;
import com.angels.andrea_resource_api.dtos.ClientTaskUpdateRequest;
import com.angels.andrea_resource_api.service.ClientTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client-tasks")
public class ClientTaskController {

    private final ClientTaskService clientTaskService;

    public ClientTaskController(ClientTaskService clientTaskService) {
        this.clientTaskService = clientTaskService;
    }

    @GetMapping
    public Flux<ClientTaskResponse> getAllClientTasks() {
        return clientTaskService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ClientTaskResponse> getClientTaskById(@PathVariable Long id) {
        return clientTaskService.findById(id);
    }

    @GetMapping("/client/{clientId}")
    public Flux<ClientTaskResponse> getTasksByClient(@PathVariable Long clientId) {
        return clientTaskService.findByClientId(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientTaskResponse> createClientTask(
            @RequestBody ClientTaskRequest request) {

        return clientTaskService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<ClientTaskResponse> updateClientTask(
            @PathVariable Long id,
            @RequestBody ClientTaskUpdateRequest request) {

        return clientTaskService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteClientTask(@PathVariable Long id) {
        return clientTaskService.deleteById(id);
    }
}