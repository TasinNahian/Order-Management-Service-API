package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;

public class ServiceOrderItemRelationship extends BaseBundled implements Serializable {
    private String id;
    private String relationshipType;
    private String serviceOrderItemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getServiceOrderItemId() {
        return serviceOrderItemId;
    }

    public void setServiceOrderItemId(String serviceOrderItemId) {
        this.serviceOrderItemId = serviceOrderItemId;
    }
}
