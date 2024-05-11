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
public class UpdateCurriculumRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Integer numberCredits;

    private String description;

    private String raeg;

    private SubjectRequest subjectRequest;

    private String createdBy;
    private Integer roleId;

}

