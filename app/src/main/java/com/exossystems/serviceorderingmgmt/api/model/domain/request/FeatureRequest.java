package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import java.util.List;

public class FeatureRequest {
    private String id;
    private boolean isBundled;
    private boolean isEnabled;
    private String name;
    private List<ConstraintRequest> constraint;
    private List<FeatureCharacteristicRequest> featureCharacteristic;
    private List<FeatureRelationshipRequest> featureRelationship;
}
