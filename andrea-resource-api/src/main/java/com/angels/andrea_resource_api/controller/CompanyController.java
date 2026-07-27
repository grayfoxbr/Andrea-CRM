package com.angels.andrea_resource_api.controller;

import com.angels.andrea_resource_api.dtos.CompanyRequest;
import com.angels.andrea_resource_api.dtos.CompanyResponse;
import com.angels.andrea_resource_api.dtos.CompanyUpdateRequest;
import com.angels.andrea_resource_api.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public Flux<CompanyResponse> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CompanyResponse> createCompany(
            @RequestBody CompanyRequest request) {

        return companyService.save(request);
    }

    @PutMapping("/{id}")
    public Mono<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyUpdateRequest request) {

        return companyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCompany(@PathVariable Long id) {
        return companyService.deleteById(id);
    }
}

