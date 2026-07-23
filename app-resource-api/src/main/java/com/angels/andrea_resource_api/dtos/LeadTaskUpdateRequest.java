package com.angels.andrea_resource_api.dtos;

import com.angels.andrea_resource_api.enums.LeadTaskStatus;

public record LeadTaskUpdateRequest(
        Long leadId,
        String agentName,
        String place,
        String contactMethod,
        String meetingLocation,
        String proposal,
        String feedback,
        LeadTaskStatus leadTaskStatus
) {
}
