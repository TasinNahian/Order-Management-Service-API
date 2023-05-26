package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.AppointmentRef;
import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.util.List;


public class ServiceOrderItemRequest extends BaseBundled {
    private String id;
    private int quantity;
    private String action;
    private AppointmentRef appointment;
    private List<ErrorMessageRequest> errorMessage;
    private ServiceRequest service;
    private List<String> serviceOrderItem;
    private List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationship;
    private String state;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AppointmentRef getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentRef appointment) {
        this.appointment = appointment;
    }

    public List<ErrorMessageRequest> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ErrorMessageRequest> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ServiceRequest getService() {
        return service;
    }

    public void setService(ServiceRequest service) {
        this.service = service;
    }

    public List<String> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<String> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }

    public List<ServiceOrderItemRelationshipRequest> getServiceOrderItemRelationship() {
        return serviceOrderItemRelationship;
    }

    public void setServiceOrderItemRelationship(List<ServiceOrderItemRelationshipRequest> serviceOrderItemRelationship) {
        this.serviceOrderItemRelationship = serviceOrderItemRelationship;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
