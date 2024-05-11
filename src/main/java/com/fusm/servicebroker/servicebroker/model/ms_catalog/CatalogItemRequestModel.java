package com.fusm.servicebroker.servicebroker.model.ms_catalog;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CatalogItemRequestModel {

    private String description;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Integer catalogId;

    private String createdBy;
    private Integer order;

}

