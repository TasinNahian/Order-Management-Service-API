package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import java.io.Serializable;

public class AppointmentRef extends BaseBundled implements Serializable {
    private String description;
    private String href;
    private String id;
    private String referredType;
    private String serviceOrderItemId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }

    public String getServiceOrderItemId() {
        return serviceOrderItemId;
    }

    public void setServiceOrderItemId(String serviceOrderItemId) {
        this.serviceOrderItemId = serviceOrderItemId;
    }
}
