package com.fusm.servicebroker.servicebroker.model.ms_catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogRequestModel {

    private String description;

    @NotEmpty
    @NotNull
    private String name;

    private Integer catalogType;
    private String createdBy;

}
