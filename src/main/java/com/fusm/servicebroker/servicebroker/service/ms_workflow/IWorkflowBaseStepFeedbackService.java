package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateStepRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateTraceability;
import org.springframework.stereotype.Service;

@Service
public interface IWorkflowBaseStepFeedbackService {

    void evaluateStep(EvaluateStepRequest evaluateStepRequest);
    void evaluateTraceability(EvaluateTraceability evaluateTraceability, Integer programId);

}
