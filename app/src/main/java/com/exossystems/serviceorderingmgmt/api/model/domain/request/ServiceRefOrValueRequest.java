package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceRefOrValueRequest extends BaseBundled implements Serializable {
    private String category;
    private String description;
    private Timestamp endDate;
    private boolean hasStarted;
    private String href;
    private String id;
    private boolean isBundle;
    private boolean isServiceEnabled;
    private boolean isStateful;
    private String name;
    private Timestamp serviceDate;
    private String serviceType;
    private Timestamp startDate;
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
    private ServiceSpecificationRefRequest serviceSpecification;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

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

    public boolean isBundle() {
        return isBundle;
    }

    public void setBundle(boolean bundle) {
        isBundle = bundle;
    }

    public boolean isServiceEnabled() {
        return isServiceEnabled;
    }

    public void setServiceEnabled(boolean serviceEnabled) {
        isServiceEnabled = serviceEnabled;
    }

    public boolean isStateful() {
        return isStateful;
    }

    public void setStateful(boolean stateful) {
        isStateful = stateful;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Timestamp serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getStartMode() {
        return startMode;
    }

    public void setStartMode(String startMode) {
        this.startMode = startMode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }

    public String getSupportingService() {
        return supportingService;
    }

    public void setSupportingService(String supportingService) {
        this.supportingService = supportingService;
    }

    public List<ServiceRelationshipRequest> getServiceRelationship() {
        return serviceRelationship;
    }

    public void setServiceRelationship(List<ServiceRelationshipRequest> serviceRelationship) {
        this.serviceRelationship = serviceRelationship;
    }

    public List<CharacteristicRequest> getServiceCharacteristics() {
        return serviceCharacteristics;
    }

    public void setServiceCharacteristics(List<CharacteristicRequest> serviceCharacteristics) {
        this.serviceCharacteristics = serviceCharacteristics;
    }

    public List<RelatedPartyRequest> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedPartyRequest> relatedParty) {
        this.relatedParty = relatedParty;
    }

    public List<FeatureRequest> getFeature() {
        return feature;
    }

    public void setFeature(List<FeatureRequest> feature) {
        this.feature = feature;
    }

    public List<RelatedPlaceRefOrValueRequest> getPlace() {
        return place;
    }

    public void setPlace(List<RelatedPlaceRefOrValueRequest> place) {
        this.place = place;
    }

    public List<RelatedEntityRefOrValueRequest> getRelatedEntity() {
        return relatedEntity;
    }

    public void setRelatedEntity(List<RelatedEntityRefOrValueRequest> relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    public List<RelatedServiceOrderItemRequest> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<RelatedServiceOrderItemRequest> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }

    public List<ResourceRefRequest> getSupportingResource() {
        return supportingResource;
    }

    public void setSupportingResource(List<ResourceRefRequest> supportingResource) {
        this.supportingResource = supportingResource;
    }

    public ServiceSpecificationRefRequest getServiceSpecification() {
        return serviceSpecification;
    }

    public void setServiceSpecification(ServiceSpecificationRefRequest serviceSpecification) {
        this.serviceSpecification = serviceSpecification;
    }
}
