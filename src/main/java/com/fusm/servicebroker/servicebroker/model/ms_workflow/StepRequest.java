package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepRequest {

    private String name;
    private Integer stepOrder;
    private Boolean isPrerrequeriment;
    private Boolean hasSummary;
    private List<StepRoleActionRequest> roleActions;
    private String createdBy;

}
