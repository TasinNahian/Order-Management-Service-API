package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
public class Note extends BaseBundled implements Serializable {
    private String author;
    private Timestamp date;
    private String id;
    private String text;
    private String serviceOrderId;
    private String serviceRefOrValueId;
}
