package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceOrderItemRef extends BaseBundled implements Serializable {
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
