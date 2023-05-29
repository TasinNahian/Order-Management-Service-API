package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
public class ServiceOrderMilestone extends BaseBundled implements Serializable {
    private String description;
    private String id;
    private String message;
    private Timestamp milestoneDate;
    private String name;
    private String status;
    private String serviceOrderId;
}