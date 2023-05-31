package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ServiceOrderJeopardyAlertRequest extends BaseBundled implements Serializable {

    private Timestamp alertDate;
    private String exception;
    private String id;
    private String jeopardyType;
    private String message;
    private String name;
    private List<ServiceOrderItemRefRequest> serviceOrderItem;

    public Timestamp getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Timestamp alertDate) {
        this.alertDate = alertDate;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJeopardyType() {
        return jeopardyType;
    }

    public void setJeopardyType(String jeopardyType) {
        this.jeopardyType = jeopardyType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceOrderItemRefRequest> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<ServiceOrderItemRefRequest> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }
}
