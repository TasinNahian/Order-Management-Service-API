package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class NoteRequest extends BaseBundled {
    private String author;
    private Timestamp date;
    private String id;
    private String text;
    private String serviceOrderId;
    private String serviceRefOrValueId;
}
