package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.CharacteristicRelationship;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CharacteristicRequest extends BaseBundled implements Serializable {
    private String id;
    private String name;
    private String value;
    private String value_type;
//    private String featureId;
//    private String serviceRefOrValueId;
//    private String serviceRelationshipId;
    List<CharacteristicRelationshipRequest> characteristicRelationship;
}
