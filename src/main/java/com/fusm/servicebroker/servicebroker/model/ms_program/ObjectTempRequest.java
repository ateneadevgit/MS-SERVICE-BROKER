package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectTempRequest {

    private String object;
    private Integer moduleId;
    private String createdBy;
    private Integer roleId;

}
