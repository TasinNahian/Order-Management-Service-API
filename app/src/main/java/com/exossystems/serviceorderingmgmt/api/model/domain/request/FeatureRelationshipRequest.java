package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import java.util.List;

public class FeatureRelationshipRequest {
    private String id;
    private String name;
    private String relationshipType;
    private List<ValidForRequest> validFor;
}
