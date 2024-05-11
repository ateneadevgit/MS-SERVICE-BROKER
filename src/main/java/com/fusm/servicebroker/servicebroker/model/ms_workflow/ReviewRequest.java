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
public class ReviewRequest {

    @NotNull
    private Integer workflowId;

    @NotNull
    private Integer stepId;

    @NotNull
    @NotEmpty
    private String review;

    private String createdBy;
    private Integer roleId;
    private Integer replyTo;

}

