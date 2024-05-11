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
public class CurriculumRequest {

    @NotNull
    @NotEmpty
    private String name;

    private Integer type;

    @NotNull
    private Integer numberCredits;

    private String description;

    private Integer fatherId;

    private String raeg;

    private SubjectRequest subjectRequest;

    private Integer workflowId;

    private Integer stepId;

    private String createdBy;

    private Integer roleId;

}

