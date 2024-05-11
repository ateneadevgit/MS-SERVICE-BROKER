package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramUpdateModel {

    @NotNull
    private FileModel fileProposal;

    private String createdBy;
    private Integer roleId;

}