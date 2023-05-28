package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

@Data
public class ServiceRelationship extends BaseBundled{
    private String href;
    private String id;
    private String relationshipType;
    private String serviceRefOrValueId;
}
