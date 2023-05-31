package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;


public class CharacteristicRelationship extends BaseBundled implements Serializable {

    private String href;
    private String id;
    private String relationshipType;
    private String characteristicId;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }
}
