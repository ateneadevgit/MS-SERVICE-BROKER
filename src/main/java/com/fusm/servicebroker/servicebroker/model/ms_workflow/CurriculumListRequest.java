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
public class CurriculumListRequest {

    private Integer workflowId;
    private Integer stepId;
    private String createdBy;
    private Integer roleId;
    private List<CurriculumRequest> curriculumRequests;

}
