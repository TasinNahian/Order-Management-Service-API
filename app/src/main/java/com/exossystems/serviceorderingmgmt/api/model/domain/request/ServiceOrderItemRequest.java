package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
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

}
