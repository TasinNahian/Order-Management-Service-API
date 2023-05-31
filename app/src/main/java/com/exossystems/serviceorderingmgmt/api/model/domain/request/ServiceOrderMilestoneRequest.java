package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ServiceOrderMilestoneRequest extends BaseBundled implements Serializable {
    private String description;
    private String id;
    private String message;
    private Timestamp milestoneDate;
    private String name;
    private String status;
    private List<ServiceOrderItemRefRequest> serviceOrderItem;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getMilestoneDate() {
        return milestoneDate;
    }

    public void setMilestoneDate(Timestamp milestoneDate) {
        this.milestoneDate = milestoneDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ServiceOrderItemRefRequest> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<ServiceOrderItemRefRequest> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }
}
