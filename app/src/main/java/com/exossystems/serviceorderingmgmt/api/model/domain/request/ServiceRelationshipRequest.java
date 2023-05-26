package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

import java.util.List;

public class ServiceRelationshipRequest extends BaseBundled {
    private String id;
    private String href;
    private String relationshipType;
    private String service;
    private List<ServiceRelationshipCharacteristicRequest> serviceRelationshipCharacteristic;

}
