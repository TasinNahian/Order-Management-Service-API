package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceOrderItemRequest extends BaseBundled implements Serializable {
    private String action;
    private String id;
    private int quantity;
    private String state;
    @JsonProperty("serviceOrderItem")
    private String otherServiceOrderItemList;
    private List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationship;
    private List<ServiceOrderItemErrorMessageRequest> errorMessage;
    private AppointmentRefRequest appointment;
    private ServiceRefOrValueRequest service;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOtherServiceOrderItemList() {
        return otherServiceOrderItemList;
    }

    public void setOtherServiceOrderItemList(String otherServiceOrderItemList) {
        this.otherServiceOrderItemList = otherServiceOrderItemList;
    }

    public List<ServiceOrderItemRelationshipRequest> getServiceOrderItemRelationship() {
        return serviceOrderItemRelationship;
    }

    public void setServiceOrderItemRelationship(List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationship) {
        this.serviceOrderItemRelationship = serviceOrderItemRelationship;
    }

    public List<ServiceOrderItemErrorMessageRequest> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ServiceOrderItemErrorMessageRequest> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AppointmentRefRequest getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentRefRequest appointment) {
        this.appointment = appointment;
    }

    public ServiceRefOrValueRequest getService() {
        return service;
    }

    public void setService(ServiceRefOrValueRequest service) {
        this.service = service;
    }
}
