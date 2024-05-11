package com.fusm.servicebroker.servicebroker.model.ms_safety_mesh;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest {

    private String name;
    private String icon;
    private String path;
    private Integer menuFather;
    private String createdBy;
    private String description;

}
