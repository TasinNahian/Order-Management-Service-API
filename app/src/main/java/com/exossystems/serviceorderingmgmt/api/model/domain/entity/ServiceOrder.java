package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ServiceOrder extends BaseBundled{
    private String id;
    private String href;
    private String category;
    private Timestamp completionDate;
    private String description;
    private Timestamp expectedCompletionDate; //requestedCompletionDate
    private String externalId;
    private String notificationContact;
    private Timestamp orderDate;
    private String priority;
    private Timestamp requestedStartDate;
    private Timestamp cancellationDate;
    private String cancellationReasion;
    private String state;

}
