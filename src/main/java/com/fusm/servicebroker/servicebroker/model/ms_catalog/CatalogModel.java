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
public class CatalogModel {

    private Integer catalogId;
    private String catalogName;
    private String description;
    private Date createdAt;
    private Integer catalogType;
    private Boolean enabled;

}

