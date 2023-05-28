package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ServiceOrderErrorMessage extends BaseBundled{
    private String id;
    private String code;
    private String message;
    private String reason;
    private String referenceError;
    private String status;
    private Timestamp timestamp;
    private String serviceOrderId;

}
