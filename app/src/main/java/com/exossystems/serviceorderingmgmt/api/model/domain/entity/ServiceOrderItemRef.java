package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getServiceOrderHref() {
        return serviceOrderHref;
    }

    public void setServiceOrderHref(String serviceOrderHref) {
        this.serviceOrderHref = serviceOrderHref;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }

    public String getServiceOrderItemRelationshipId() {
        return serviceOrderItemRelationshipId;
    }

    public void setServiceOrderItemRelationshipId(String serviceOrderItemRelationshipId) {
        this.serviceOrderItemRelationshipId = serviceOrderItemRelationshipId;
    }

    public String getServiceOrderErrorMessageId() {
        return serviceOrderErrorMessageId;
    }

    public void setServiceOrderErrorMessageId(String serviceOrderErrorMessageId) {
        this.serviceOrderErrorMessageId = serviceOrderErrorMessageId;
    }

    public String getServiceOrderMilestoneId() {
        return serviceOrderMilestoneId;
    }

    public void setServiceOrderMilestoneId(String serviceOrderMilestoneId) {
        this.serviceOrderMilestoneId = serviceOrderMilestoneId;
    }

    public String getServiceOrderJeopardyAlertId() {
        return serviceOrderJeopardyAlertId;
    }

    public void setServiceOrderJeopardyAlertId(String serviceOrderJeopardyAlertId) {
        this.serviceOrderJeopardyAlertId = serviceOrderJeopardyAlertId;
    }
}
