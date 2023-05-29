package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ValidFor implements Serializable {
    private String id;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
}
