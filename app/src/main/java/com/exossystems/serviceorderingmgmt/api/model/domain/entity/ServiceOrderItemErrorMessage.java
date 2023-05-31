package com.exossystems.serviceorderingmgmt.api.model.domain.entity;


import java.io.Serializable;
import java.sql.Timestamp;
public class ServiceOrderItemErrorMessage extends BaseBundled implements Serializable {

    private String id;
    private String code;
    private String message;
    private String reason;
    private String referenceError;
    private String status;
    private Timestamp timestamp;
    private String serviceOrderItemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReferenceError() {
        return referenceError;
    }

    public void setReferenceError(String referenceError) {
        this.referenceError = referenceError;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getServiceOrderItemId() {
        return serviceOrderItemId;
    }

    public void setServiceOrderItemId(String serviceOrderItemId) {
        this.serviceOrderItemId = serviceOrderItemId;
    }
}
