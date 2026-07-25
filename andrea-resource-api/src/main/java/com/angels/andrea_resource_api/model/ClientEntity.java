package com.angels.andrea_resource_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("client_entity")
public class ClientEntity {

    @Id
    private Long clientId;

    /** FK para {@link Company}. */
    private Long companyId;

    /** FK para o {@link Lead} de origem (quando o cliente nasceu de um lead convertido). */
    private Long leadId;

    public ClientEntity() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }
}
