package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import java.io.Serializable;


public class ServiceSpecificationRef extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String name;
    private String version;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
