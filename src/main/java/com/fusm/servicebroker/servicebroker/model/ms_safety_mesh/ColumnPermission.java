package com.fusm.servicebroker.servicebroker.model.ms_safety_mesh;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnPermission {

    private String columnName;
    private String dataType;
    private String tableOrigin;

}

