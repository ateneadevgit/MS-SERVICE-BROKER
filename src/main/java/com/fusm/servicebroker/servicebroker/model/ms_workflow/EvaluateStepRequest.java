package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateStepRequest {

    @NonNull
    @NotEmpty
    @Pattern(regexp = "^(review|completeness|approved|summary)$")
    private String feedbackStatus;

    private String feedback;

    @NonNull
    private Integer workflowId;

    @NonNull
    private Integer stepId;

    private Integer roleId;
    private String createdBy;
    private List<Integer> campusId;

    @NonNull
    private Boolean isSummary;

}
