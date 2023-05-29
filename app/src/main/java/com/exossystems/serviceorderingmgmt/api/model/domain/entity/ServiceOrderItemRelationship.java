package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceOrderItemRelationship extends BaseBundled implements Serializable {
    private String id;
    private String relationshipType;
    private String serviceOrderItemId;
}
