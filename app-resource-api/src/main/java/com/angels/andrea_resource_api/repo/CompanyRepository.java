package com.angels.andrea_resource_api.repo;

import com.angels.andrea_resource_api.model.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompanyRepository extends ReactiveCrudRepository<Company, Long> {
}
