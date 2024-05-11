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
public class CurriculumSummaryRequest {

    @NotNull
    @NotEmpty
    private String summary;

    @NotNull
    private Integer curriculumType;

    @NotNull
    private Integer stepId;

    @NotNull
    private Integer workflowId;

    private Integer roleId;
    private String createdBy;

}

