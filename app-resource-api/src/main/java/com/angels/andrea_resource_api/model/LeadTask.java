package com.angels.andrea_resource_api.model;

import com.angels.andrea_resource_api.enums.LeadTaskStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Corresponde ao nó "Task Lead" do diagrama de classes, consumido por
 * LeadTaskController / LeadTaskService.
 */
@Table("lead_tasks")
public class LeadTask {

    @Id
    private Long leadTaskId;

    /** FK para o {@link Lead} ao qual esta tarefa pertence. */
    private Long leadId;

    private String agentName;
    private String place;
    private String contactMethod;
    private String meetingLocation;
    private String proposal;
    private String feedback;
    private LeadTaskStatus leadTaskStatus;

    public LeadTask() {
    }

    public Long getLeadTaskId() {
        return leadTaskId;
    }

    public void setLeadTaskId(Long leadTaskId) {
        this.leadTaskId = leadTaskId;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LeadTaskStatus getStatus() {
        return leadTaskStatus;
    }

    public void setStatus(LeadTaskStatus status) {
        this.leadTaskStatus = status;
    }
}
