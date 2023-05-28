package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class FeatureRelationship {
    private String id;
    private String name;
    private String relationshipType;
    private String validFor;
    private String featureId;
}
