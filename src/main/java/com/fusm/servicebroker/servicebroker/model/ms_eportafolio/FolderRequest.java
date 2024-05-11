package com.fusm.servicebroker.servicebroker.model.ms_eportafolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderRequest {

    private String name;
    private String icon;
    private String color;
    private String createdBy;

}