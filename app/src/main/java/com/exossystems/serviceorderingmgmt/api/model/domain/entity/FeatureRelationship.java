package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;


public class FeatureRelationship implements Serializable {
    private String id;
    private String name;
    private String relationshipType;
    private String featureId;
    private String validForId;

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

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getValidForId() {
        return validForId;
    }

    public void setValidForId(String validForId) {
        this.validForId = validForId;
    }
}
