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
public class SubjectRequest {

    @NotNull
    private Integer semester;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    private Integer hoursInteractionTeacher;

    @NotNull
    private Integer hourSelfWork;

}
