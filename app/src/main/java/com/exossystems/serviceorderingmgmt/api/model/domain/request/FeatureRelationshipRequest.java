package com.exossystems.serviceorderingmgmt.api.model.domain.request;


import java.io.Serializable;

public class FeatureRelationshipRequest implements Serializable {
    private String id;
    private String name;
    private String relationshipType;
    private String featureId;
    private ValidForRequest validFor;

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

    public ValidForRequest getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidForRequest validFor) {
        this.validFor = validFor;
    }
}
