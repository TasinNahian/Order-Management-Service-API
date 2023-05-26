package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.Place;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.RelatedParty;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ResourceRef;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceSpecificationRef;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
public class ServiceRequest {
    private String id;
    private String href;
    private String category;
    private String description;
    private Timestamp endDate;
    private boolean hasStarted;
    private boolean isBundle;
    private boolean isServiceEnabled;
    private boolean isStateful;
    private String name;
    private String serviceDate;
    private String serviceType;
    private Timestamp startDate;
    private String startMode;
    private List<FeatureRequest> feature;
    private List<NoteRequest> note;
    private List<Place> place;
    private List<RelatedParty> relatedEntity;
    private List<RelatedParty> relatedParty;
    private List<ServiceCharacteristicRequest> serviceCharacteristic;
    @JsonProperty("serviceOrderItem")
    private List<Item> item;
    private List<ServiceRelationshipRequest> serviceRelationship;
    private ServiceSpecificationRef serviceSpecification;
    private String state;
    private List<ResourceRef> supportingResource;
    private List<String> supportingService;
    @JsonProperty("@referredType")
    private String referredType;
}
