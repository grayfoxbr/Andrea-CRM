package com.angels.andrea_resource_api.dtos;

public record ClientResponse(
        Long clientId,
        Long companyId,
        Long leadId
) {
}
