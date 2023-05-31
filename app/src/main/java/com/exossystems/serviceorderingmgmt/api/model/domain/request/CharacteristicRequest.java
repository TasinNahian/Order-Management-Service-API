package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

import java.io.Serializable;
import java.util.List;

public class CharacteristicRequest extends BaseBundled implements Serializable {
    private String id;
    private String name;
    private String value;
    private String value_type;
//    private String featureId;
//    private String serviceRefOrValueId;
//    private String serviceRelationshipId;
    List<CharacteristicRelationshipRequest> characteristicRelationship;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue_type() {
        return value_type;
    }

    public void setValue_type(String value_type) {
        this.value_type = value_type;
    }

    public List<CharacteristicRelationshipRequest> getCharacteristicRelationship() {
        return characteristicRelationship;
    }

    public void setCharacteristicRelationship(List<CharacteristicRelationshipRequest> characteristicRelationship) {
        this.characteristicRelationship = characteristicRelationship;
    }
}
