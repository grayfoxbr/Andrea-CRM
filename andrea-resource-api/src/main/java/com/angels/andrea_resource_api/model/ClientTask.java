package com.angels.andrea_resource_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * Corresponde ao nó "Task Client" do diagrama de classes, consumido por
 * ClientTaskController / ClientTaskService.
 */
@Table("client_tasks")
public class ClientTask {

    @Id
    private Long taskClientId;

    /** FK para o {@link ClientEntity} ao qual esta tarefa pertence. */
    private Long clientId;

    private String meetingTopic;
    private LocalDate taskBegin;
    private LocalDate taskEnd;
    private String projectDescription;
    private String notes;

    public ClientTask() {
    }

    public Long getTaskClientId() {
        return taskClientId;
    }

    public void setTaskClientId(Long taskClientId) {
        this.taskClientId = taskClientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public LocalDate getTaskBegin() {
        return taskBegin;
    }

    public void setTaskBegin(LocalDate taskBegin) {
        this.taskBegin = taskBegin;
    }

    public LocalDate getTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(LocalDate taskEnd) {
        this.taskEnd = taskEnd;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
