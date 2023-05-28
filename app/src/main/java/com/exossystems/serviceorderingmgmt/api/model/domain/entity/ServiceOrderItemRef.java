package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceOrderItemRef extends BaseBundled{
    private String id;
    private String itemId;
    private String serviceOrderHref;
    private String serviceOrderId;
    private String referredType;
    private String serviceOrderItemRelationshipId;
    private String serviceOrderErrorMessageId;
    private String serviceOrderMilestoneId;
    private String serviceOrderJeopardyAlertId;
}
