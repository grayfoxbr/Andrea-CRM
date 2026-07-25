package com.angels.andrea_resource_api.controller;

import com.angels.andrea_resource_api.dtos.LeadRequest;
import com.angels.andrea_resource_api.dtos.LeadResponse;
import com.angels.andrea_resource_api.dtos.LeadUpdateRequest;
import com.angels.andrea_resource_api.model.Lead;
import com.angels.andrea_resource_api.service.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public Flux<LeadResponse> getAllLeads() {
        return leadService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<LeadResponse> getLeadById(@PathVariable Long id) {
        return leadService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LeadResponse> createLead(@RequestBody LeadRequest request) {
        return leadService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<LeadResponse> updateLead(
            @PathVariable Long id,
            @RequestBody LeadUpdateRequest request) {

        return leadService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteLead(@PathVariable Long id) {
        return leadService.deleteById(id);
    }
}
