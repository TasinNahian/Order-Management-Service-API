package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ServiceOrderItemRefRequest extends BaseBundled implements Serializable {
    private String id;
    private String itemId;
    private String serviceOrderHref;
    private String serviceOrderId;
    @JsonProperty("@referredType")
    private String referredType;
//    private String serviceOrderItemRelationshipId;
//    private String serviceOrderErrorMessageId;
//    private String serviceOrderMilestoneId;
//    private String serviceOrderJeopardyAlertId;

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
}
