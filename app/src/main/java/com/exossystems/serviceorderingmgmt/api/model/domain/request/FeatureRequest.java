package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class FeatureRequest {
    private String id;
    private boolean isBundle;
    private boolean isEnabled;
    private String name;
//    private String serviceRefOrValueId;
    private List<CharacteristicRequest> featureCharacteristic;
    private List<ConstraintRequest> constraint;
    private List<FeatureRelationshipRequest> featureRelationship;

}
