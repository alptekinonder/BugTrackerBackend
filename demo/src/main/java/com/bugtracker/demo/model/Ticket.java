package com.bugtracker.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long projectId;
    @Column
    private String ticketTitle;
    @Column
    private String ticketDescription;
    @Column
    private String assignedDeveloper;
    @Column
    private String createDate;
    @Column
    private String assignDate;

    public Ticket(){

    }    
    public Ticket(String ticketTitle, String ticketDescription,String assignDate,String createDate, String assignedDeveloper,Long projectId){
        super();
        this.assignedDeveloper = assignedDeveloper;
        this.assignDate = assignDate;
        this.projectId = projectId;
        this.createDate = createDate;
        this.assignedDeveloper = assignedDeveloper;
        this.ticketDescription = ticketDescription;
        this.ticketTitle= ticketTitle;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getTicketTitle() {
        return this.ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }
    public String getTicketDescription() {
        return this.ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getAssignedDeveloper() {
        return this.assignedDeveloper;
    }

    public void setAssignedDeveloper(String assignedDeveloper) {
        this.assignedDeveloper = assignedDeveloper;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAssignDate() {
        return this.assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

}
