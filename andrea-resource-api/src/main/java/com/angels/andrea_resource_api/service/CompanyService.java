package com.angels.andrea_resource_api.service;

import com.angels.andrea_resource_api.cache.ReactiveCacheService;
import com.angels.andrea_resource_api.dtos.CompanyRequest;
import com.angels.andrea_resource_api.dtos.CompanyResponse;
import com.angels.andrea_resource_api.dtos.CompanyUpdateRequest;
import com.angels.andrea_resource_api.model.Company;
import com.angels.andrea_resource_api.repo.CompanyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CompanyService {

    private static final String CACHE_PREFIX = "company:id:";
    private static final Duration TTL = Duration.ofMinutes(10);

    private final CompanyRepository companyRepository;
    private final ReactiveCacheService cache;

    public CompanyService(CompanyRepository companyRepository,
                          ReactiveCacheService cache) {
        this.companyRepository = companyRepository;
        this.cache = cache;
    }

    public Flux<CompanyResponse> findAll() {
        return companyRepository.findAll()
                .map(this::toResponse);
    }

    public Mono<CompanyResponse> findById(Long id) {
        return cache.cacheable(
                        CACHE_PREFIX + id,
                        Company.class,
                        TTL,
                        () -> companyRepository.findById(id))
                .map(this::toResponse);
    }

    public Mono<CompanyResponse> save(CompanyRequest request) {

        Company entity = new Company();
        entity.setCompanyName(request.companyName());
        entity.setCnpj(request.cnpj());
        entity.setDescription(request.description());
        entity.setCompanyEmail(request.companyEmail());
        entity.setCompanyPhoneNumber(request.companyPhoneNumber());
        entity.setBusinessArea(request.businessArea());

        return companyRepository.save(entity)
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getCompanyId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<CompanyResponse> update(Long id,
                                        CompanyUpdateRequest request) {

        return companyRepository.findById(id)
                .flatMap(entity -> {

                    entity.setCompanyName(request.companyName());
                    entity.setCnpj(request.cnpj());
                    entity.setDescription(request.description());
                    entity.setCompanyEmail(request.companyEmail());
                    entity.setCompanyPhoneNumber(request.companyPhoneNumber());
                    entity.setBusinessArea(request.businessArea());

                    return companyRepository.save(entity);
                })
                .flatMap(saved ->
                        cache.evict(CACHE_PREFIX + saved.getCompanyId())
                                .thenReturn(saved))
                .map(this::toResponse);
    }

    public Mono<Void> deleteById(Long id) {

        return companyRepository.deleteById(id)
                .then(cache.evict(CACHE_PREFIX + id))
                .then();
    }

    private CompanyResponse toResponse(Company entity) {

        return new CompanyResponse(
                entity.getCompanyId(),
                entity.getCompanyName(),
                entity.getCnpj(),
                entity.getDescription(),
                entity.getCompanyEmail(),
                entity.getCompanyPhoneNumber(),
                entity.getBusinessArea()
        );
    }
}

