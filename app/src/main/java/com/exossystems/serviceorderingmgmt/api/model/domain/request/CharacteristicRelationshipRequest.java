package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

@Data
public class CharacteristicRelationshipRequest extends BaseBundled {

    private String href;
    private String id;
    private String relationshipType;
//    private String characteristicId;
}
