package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;


public class ExternalReference extends BaseBundled implements Serializable {
    private String externalReferenceType;
    private String href;
    private String id;
    private String name;
    private String serviceOrderId;

    public String getExternalReferenceType() {
        return externalReferenceType;
    }

    public void setExternalReferenceType(String externalReferenceType) {
        this.externalReferenceType = externalReferenceType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }
}
