package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepAttachRequest {

    @NotNull
    private List<AttachRequest> attachments;

    @NotNull
    private Integer workflowId;

    @NotNull
    private Integer stepId;

    private Integer roleId;
    private String createdBy;

}