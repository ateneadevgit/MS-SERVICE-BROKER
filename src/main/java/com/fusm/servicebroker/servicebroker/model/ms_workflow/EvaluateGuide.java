package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateGuide {

    private String feedbackStatus;
    private Integer roleId;
    private String createdBy;
    private String userId;

}