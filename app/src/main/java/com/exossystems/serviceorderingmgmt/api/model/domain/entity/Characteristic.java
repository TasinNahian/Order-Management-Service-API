package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;


public class Characteristic extends BaseBundled implements Serializable {
    private String id;
    private String name;
    private String value;
    private String value_type;
    private String featureId;
    private String serviceRefOrValueId;
    private String serviceRelationshipId;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue_type() {
        return value_type;
    }

    public void setValue_type(String value_type) {
        this.value_type = value_type;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getServiceRefOrValueId() {
        return serviceRefOrValueId;
    }

    public void setServiceRefOrValueId(String serviceRefOrValueId) {
        this.serviceRefOrValueId = serviceRefOrValueId;
    }

    public String getServiceRelationshipId() {
        return serviceRelationshipId;
    }

    public void setServiceRelationshipId(String serviceRelationshipId) {
        this.serviceRelationshipId = serviceRelationshipId;
    }
}
