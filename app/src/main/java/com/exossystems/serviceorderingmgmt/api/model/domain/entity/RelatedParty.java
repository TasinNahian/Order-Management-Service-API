package com.exossystems.serviceorderingmgmt.api.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RelatedParty extends BaseBundled implements Serializable {
    private String href;
    private String id;
    private String name;
    private String role;
    private String referredType;
    private String serviceOrderId;
    private String serviceRefOrValueId;

}
