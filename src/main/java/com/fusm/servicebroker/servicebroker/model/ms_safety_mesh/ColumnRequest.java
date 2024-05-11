package com.fusm.servicebroker.servicebroker.model.ms_safety_mesh;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnRequest {

    private Integer roleId;
    private Integer moduleId;
    private List<ColumnPermission> columns;

}
