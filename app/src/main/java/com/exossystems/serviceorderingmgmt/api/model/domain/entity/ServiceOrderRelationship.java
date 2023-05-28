package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServiceOrderRelationship extends BaseBundled{
    private String href;
    private String id;
    private String relationshipType;
    private String referredType;
    private String serviceOrderId;
}
