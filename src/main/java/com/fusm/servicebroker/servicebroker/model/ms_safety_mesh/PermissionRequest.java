package com.fusm.servicebroker.servicebroker.model.ms_safety_mesh;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {

    @NotNull
    private Integer moduleId;

    @NotNull
    private Integer roleId;

    @NotNull
    private Boolean hasView;

    @NotNull
    private Boolean hasWrite;

    @NotNull
    private Boolean hasEdit;

    @NotNull
    private Boolean hasDelete;

    private String createdBy;

}
