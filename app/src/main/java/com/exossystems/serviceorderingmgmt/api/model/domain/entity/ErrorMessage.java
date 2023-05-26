package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import java.sql.Timestamp;

public class ErrorMessage extends BaseBundled{
    private String id;
    private String code;
    private String message;
    private String reason;
    private String referenceError;
    private String status;
    private Timestamp timestamp;
    private String serviceOrderItemId;

}
