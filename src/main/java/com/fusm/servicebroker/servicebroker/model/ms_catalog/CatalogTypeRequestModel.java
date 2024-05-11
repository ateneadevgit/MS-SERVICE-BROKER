package com.fusm.servicebroker.servicebroker.model.ms_catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogTypeRequestModel {

    @NotNull
    @NotEmpty
    private String type;

}
