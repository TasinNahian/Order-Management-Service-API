package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import java.io.Serializable;
import java.sql.Timestamp;

public class ValidForRequest implements Serializable {
    private Timestamp startDateTime;
    private Timestamp endDateTime;
}

