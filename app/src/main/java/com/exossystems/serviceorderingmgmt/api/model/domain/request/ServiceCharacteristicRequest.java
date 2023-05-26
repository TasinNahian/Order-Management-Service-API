package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.util.List;

@Data
public class ServiceCharacteristicRequest extends BaseBundled {
    private String id;
    private String name;
    private String valueType;
    private List<CharacteristicRelationshipRequest> characteristicRelationship;
    private String value;

}
