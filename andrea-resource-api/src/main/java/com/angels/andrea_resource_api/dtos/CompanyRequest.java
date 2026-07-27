package com.angels.andrea_resource_api.dtos;

public record CompanyRequest(
        String companyName,
        String cnpj,
        String description,
        String companyEmail,
        String companyPhoneNumber,
        String businessArea
) {
}