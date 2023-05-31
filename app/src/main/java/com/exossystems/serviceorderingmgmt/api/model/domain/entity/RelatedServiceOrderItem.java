package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;

public class RelatedServiceOrderItem extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String itemAction;
    private String itemId;
    private String role;
    private String serviceOrderHref;
    private String serviceOrderId;
    private String referredType;
    private String serviceRefOrValueId;

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

    public String getItemAction() {
        return itemAction;
    }

    public void setItemAction(String itemAction) {
        this.itemAction = itemAction;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getServiceRefOrValueId() {
        return serviceRefOrValueId;
    }

    public void setServiceRefOrValueId(String serviceRefOrValueId) {
        this.serviceRefOrValueId = serviceRefOrValueId;
    }
}
