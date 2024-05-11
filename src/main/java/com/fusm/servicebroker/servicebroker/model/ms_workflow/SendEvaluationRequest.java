package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEvaluationRequest {

    @NotNull
    private List<Integer> attachment;

    @NotNull
    private Integer workflowId;

    @NotNull
    private Integer stepId;

    private String createdBy;
    private Integer roleId;

}
