package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ServiceOrderJeopardyAlert extends BaseBundled implements Serializable {

    private Timestamp alertDate;
    private String exception;
    private String id;
    private String jeopardyType;
    private String message;
    private String name;
    private String serviceOrderId;

}
