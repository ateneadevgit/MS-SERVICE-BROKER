package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateProposalModel {

    @NonNull
    @NotEmpty
    @Pattern(regexp = "^(review|declined|completeness|approved|disabled)$")
    private String evaluation;

    @Size(min = 1, max = 255)
    private String feedback;

    private FileModel fileFeedback;
    private String createdBy;
    private Integer roleId;

}
