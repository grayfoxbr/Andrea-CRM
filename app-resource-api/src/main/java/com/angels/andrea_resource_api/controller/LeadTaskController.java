package com.angels.andrea_resource_api.controller;

import com.angels.andrea_resource_api.dtos.LeadTaskRequest;
import com.angels.andrea_resource_api.dtos.LeadTaskResponse;
import com.angels.andrea_resource_api.dtos.LeadTaskUpdateRequest;
import com.angels.andrea_resource_api.service.LeadTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/lead-tasks")
public class LeadTaskController {

    private final LeadTaskService leadTaskService;

    public LeadTaskController(LeadTaskService leadTaskService) {
        this.leadTaskService = leadTaskService;
    }

    @GetMapping
    public Flux<LeadTaskResponse> getAllLeadTasks() {
        return leadTaskService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<LeadTaskResponse> getLeadTaskById(@PathVariable Long id) {
        return leadTaskService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LeadTaskResponse> createLeadTask(
            @RequestBody LeadTaskRequest request) {

        return leadTaskService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<LeadTaskResponse> updateLeadTask(
            @PathVariable Long id,
            @RequestBody LeadTaskUpdateRequest request) {

        return leadTaskService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteLeadTask(@PathVariable Long id) {
        return leadTaskService.deleteById(id);
    }
}