package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceOrderItemRelationship extends BaseBundled{
    private String id;
    private String relationshipType;
    private String serviceOrderItemId;
}
