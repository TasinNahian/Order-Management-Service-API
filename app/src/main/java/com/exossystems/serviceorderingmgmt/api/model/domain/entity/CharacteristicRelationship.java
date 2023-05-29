package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CharacteristicRelationship extends BaseBundled implements Serializable {

    private String href;
    private String id;
    private String relationshipType;
    private String characteristicId;
}
