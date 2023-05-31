package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ValidFor implements Serializable {
    private String id;
    private Timestamp startDateTime;
    private Timestamp endDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }
}
