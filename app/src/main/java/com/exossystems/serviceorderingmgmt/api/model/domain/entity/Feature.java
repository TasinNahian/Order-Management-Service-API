package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;

public class Feature implements Serializable {
    private String id;
    private boolean isBundle;
    private boolean isEnabled;
    private String name;
    private String serviceRefOrValueId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBundle() {
        return isBundle;
    }

    public void setBundle(boolean bundle) {
        isBundle = bundle;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceRefOrValueId() {
        return serviceRefOrValueId;
    }

    public void setServiceRefOrValueId(String serviceRefOrValueId) {
        this.serviceRefOrValueId = serviceRefOrValueId;
    }
}
