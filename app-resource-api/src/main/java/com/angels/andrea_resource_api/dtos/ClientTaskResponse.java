package com.angels.andrea_resource_api.dtos;

import java.time.LocalDate;

public record ClientTaskResponse(
        Long taskClientId,
        Long clientId,
        String meetingTopic,
        LocalDate taskBegin,
        LocalDate taskEnd,
        String projectDescription,
        String notes
) {
}