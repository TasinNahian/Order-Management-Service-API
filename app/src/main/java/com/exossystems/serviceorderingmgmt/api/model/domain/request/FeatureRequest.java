package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import java.io.Serializable;
import java.util.List;


public class FeatureRequest implements Serializable {
    private String id;
    private boolean isBundle;
    private boolean isEnabled;
    private String name;
//    private String serviceRefOrValueId;
    private List<CharacteristicRequest> featureCharacteristic;
    private List<ConstraintRequest> constraint;
    private List<FeatureRelationshipRequest> featureRelationship;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBundle() {
        return isBundle;
    }

    public void setBundle(boolean bundle) {
        isBundle = bundle;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacteristicRequest> getFeatureCharacteristic() {
        return featureCharacteristic;
    }

    public void setFeatureCharacteristic(List<CharacteristicRequest> featureCharacteristic) {
        this.featureCharacteristic = featureCharacteristic;
    }

    public List<ConstraintRequest> getConstraint() {
        return constraint;
    }

    public void setConstraint(List<ConstraintRequest> constraint) {
        this.constraint = constraint;
    }

    public List<FeatureRelationshipRequest> getFeatureRelationship() {
        return featureRelationship;
    }

    public void setFeatureRelationship(List<FeatureRelationshipRequest> featureRelationship) {
        this.featureRelationship = featureRelationship;
    }
}
