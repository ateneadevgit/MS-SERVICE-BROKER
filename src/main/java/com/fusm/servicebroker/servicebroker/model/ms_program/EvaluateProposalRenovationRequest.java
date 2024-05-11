package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateProposalRenovationRequest {

    @NotNull
    @NotEmpty
    private String evaluation;

    @NotNull
    private FileModel fileFeedback;

    @NotNull
    private List<Integer> approvedModules;


    private String createdBy;
    private Integer roleId;

}
