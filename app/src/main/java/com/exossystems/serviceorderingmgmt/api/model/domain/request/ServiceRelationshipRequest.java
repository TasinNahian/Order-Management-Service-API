package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.util.List;

@Data
public class ServiceRelationshipRequest extends BaseBundled {
    private String href;
    private String id;
    private String relationshipType;
//    private String serviceRefOrValueId;
    private List<CharacteristicRequest> serviceRelationshipCharacteristic;
}
