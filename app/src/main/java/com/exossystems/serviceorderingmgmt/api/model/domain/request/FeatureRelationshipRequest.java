package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeatureRelationshipRequest implements Serializable {
    private String id;
    private String name;
    private String relationshipType;
    private String featureId;
    private ValidForRequest validFor;
}
