package com.angels.andrea_resource_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("leads")
public class Lead {

    @Id
    private Long leadId;

    /** FK para {@link Company}. */
    private Long companyId;

    private Boolean isLead;
    private String phoneNumber;
    private String email;
    private LocalDate date;
    private String comunicationChannel;
    private String agentName;
    private String location;
    private String offer;

    public Lead() {
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Boolean getIsLead() {
        return isLead;
    }

    public void setIsLead(Boolean isLead) {
        this.isLead = isLead;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComunicationChannel() {
        return comunicationChannel;
    }

    public void setComunicationChannel(String comunicationChannel) {
        this.comunicationChannel = comunicationChannel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
