package com.angels.andrea_resource_api.dtos;


import java.time.LocalDate;

public record LeadResponse(
        Long leadId,
        Long companyId,
        Boolean isLead,
        String phoneNumber,
        String email,
        LocalDate date,
        String comunicationChannel,
        String agentName,
        String location,
        String offer
) {
}