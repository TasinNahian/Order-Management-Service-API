package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ServiceOrder extends BaseBundled{
    private Timestamp cancellationDate;
    private String cancellationReason;
    private String category;
    private Timestamp completionDate;
    private String description;
    private Timestamp expectedCompletionDate; //requestedCompletionDate
    private String externalId;
    private String href;
    private String id;
    private String notificationContact;
    private Timestamp orderDate;
    private String priority;
    private Timestamp requestedCompletionDate;
    private Timestamp requestedStartDate;
    private String startDate;
    private String state;

}
