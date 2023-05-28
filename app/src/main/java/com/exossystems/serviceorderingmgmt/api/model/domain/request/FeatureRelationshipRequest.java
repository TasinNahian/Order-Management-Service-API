package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import lombok.Data;

@Data
public class FeatureRelationshipRequest {
    private String id;
    private String name;
    private String relationshipType;
    private String validFor;
    private String featureId;
}
