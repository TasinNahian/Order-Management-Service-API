package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.io.Serializable;

@Data
public class CharacteristicRelationshipRequest extends BaseBundled implements Serializable {

    private String href;
    private String id;
    private String relationshipType;
//    private String characteristicId;
}
