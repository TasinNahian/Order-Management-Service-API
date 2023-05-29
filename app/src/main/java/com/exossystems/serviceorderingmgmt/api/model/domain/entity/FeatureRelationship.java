package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeatureRelationship implements Serializable {
    private String id;
    private String name;
    private String relationshipType;
    private String featureId;
    private String validForId;
}
