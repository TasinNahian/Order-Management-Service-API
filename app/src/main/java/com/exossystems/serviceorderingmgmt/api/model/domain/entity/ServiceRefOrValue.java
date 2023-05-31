package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceRefOrValue extends BaseBundled implements Serializable {
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
    private String referredType;
    private String serviceOrderItemId;
    private String supportingService;
    private String serviceRelationshipId;

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

    public String getServiceOrderItemId() {
        return serviceOrderItemId;
    }

    public void setServiceOrderItemId(String serviceOrderItemId) {
        this.serviceOrderItemId = serviceOrderItemId;
    }

    public String getSupportingService() {
        return supportingService;
    }

    public void setSupportingService(String supportingService) {
        this.supportingService = supportingService;
    }

    public String getServiceRelationshipId() {
        return serviceRelationshipId;
    }

    public void setServiceRelationshipId(String serviceRelationshipId) {
        this.serviceRelationshipId = serviceRelationshipId;
    }
}
