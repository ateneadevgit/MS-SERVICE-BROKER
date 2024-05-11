package com.fusm.servicebroker.servicebroker.model.ms_workflow;

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
public class WorkflowBaseRequest {

    @NotNull
    private UserData userData;

    @NotNull
    private Integer workflowObjectId;

    @NotNull
    @NotEmpty
    private String workflowType;

    private String createdBy;

    private Integer roleId;

}
