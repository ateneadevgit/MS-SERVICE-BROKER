package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateTraceability {

    private String feedbackStatus;
    private FileModel fileFeedback;
    private Integer roleId;
    private String createdBy;

}