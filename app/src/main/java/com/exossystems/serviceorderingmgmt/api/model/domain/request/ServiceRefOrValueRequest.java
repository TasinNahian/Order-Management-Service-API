package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ServiceRefOrValueRequest extends BaseBundled implements Serializable {
    private String category;
    private String description;
    private String endDate;
    private boolean hasStarted;
    private String href;
    private String id;
    private boolean isBundle;
    private boolean isServiceEnabled;
    private boolean isStateful;
    private String name;
    private String serviceDate;
    private String serviceType;
    private String startDate;
    private String startMode;
    private String state;
    @JsonProperty("@referredType")
    private String referredType;
//    private String serviceOrderItemId;
    private String supportingService;
//    private String serviceRelationshipId;
    private List<ServiceRelationshipRequest> serviceRelationship;
    private List<CharacteristicRequest> serviceCharacteristics;
    private List<RelatedPartyRequest> relatedParty;
    private List<FeatureRequest> feature;
    private List<RelatedPlaceRefOrValueRequest> place;
    private List<RelatedEntityRefOrValueRequest> relatedEntity;
    private List<RelatedServiceOrderItemRequest> serviceOrderItem;
    private List<ResourceRefRequest> supportingResource;
    private List<ServiceSpecificationRefRequest> serviceSpecification;
}
