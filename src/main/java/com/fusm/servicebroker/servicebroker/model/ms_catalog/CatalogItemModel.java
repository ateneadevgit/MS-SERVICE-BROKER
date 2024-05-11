package com.fusm.servicebroker.servicebroker.model.ms_catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItemModel {

    private Integer catalogItemId;
    private String catalogItemName;
    private String catalogItemDescription;
    private Boolean enabled;
    private Integer catalogId;
    private Date createdAt;
}

