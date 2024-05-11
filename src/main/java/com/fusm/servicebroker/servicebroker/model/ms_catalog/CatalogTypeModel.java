package com.fusm.servicebroker.servicebroker.model.ms_catalog;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogTypeModel {

    private Integer catalogTypeId;
    private String type;
    private Boolean enabled;
    private Date createdAt;
    private String createdBy;

}
