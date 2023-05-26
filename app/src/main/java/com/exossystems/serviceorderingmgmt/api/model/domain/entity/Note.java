package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Note extends BaseBundled{
    private String id;
    private String author;
    private Timestamp date;
    private String system;
    private String text;
    private String serviceOrderId;
    private String serviceId;
}
