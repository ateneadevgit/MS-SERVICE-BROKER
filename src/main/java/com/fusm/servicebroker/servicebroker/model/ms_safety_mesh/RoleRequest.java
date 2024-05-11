package com.fusm.servicebroker.servicebroker.model.ms_safety_mesh;

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
public class RoleRequest {

    @NotEmpty
    @NotNull
    private String nameRole;

    @NotEmpty
    @NotNull
    private String sinuId;

    private String createdBy;

    private String description;

}