package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;


import java.io.Serializable;
import java.util.List;

public class ServiceRelationshipRequest extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String relationshipType;
//    private String serviceRefOrValueId;
    private List<CharacteristicRequest> serviceRelationshipCharacteristic;
    private ServiceRefOrValueRequest service;

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

    public List<CharacteristicRequest> getServiceRelationshipCharacteristic() {
        return serviceRelationshipCharacteristic;
    }

    public void setServiceRelationshipCharacteristic(List<CharacteristicRequest> serviceRelationshipCharacteristic) {
        this.serviceRelationshipCharacteristic = serviceRelationshipCharacteristic;
    }

    public ServiceRefOrValueRequest getService() {
        return service;
    }

    public void setService(ServiceRefOrValueRequest service) {
        this.service = service;
    }
}
