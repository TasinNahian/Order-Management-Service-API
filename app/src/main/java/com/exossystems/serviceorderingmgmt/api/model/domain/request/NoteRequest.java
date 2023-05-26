package com.exossystems.serviceorderingmgmt.api.model.domain.request;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.BaseBundled;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class NoteRequest extends BaseBundled {
    private List<String> id;
    private List<String> author;
    private List<Timestamp> date;
    private List<String> text;

}
