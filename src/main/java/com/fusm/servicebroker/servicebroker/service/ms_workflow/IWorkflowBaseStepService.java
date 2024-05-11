package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.SendEvaluationRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepAttachRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowStepRequest;
import org.springframework.stereotype.Service;

@Service
public interface IWorkflowBaseStepService {

    Object getStepsOfWorkflowByRole(Integer objectId, String workflowType);
    void loadAttachment(StepAttachRequest stepAttachRequest);
    void disableAttachment(Integer programId);
    void sendStepToEvaluation(SendEvaluationRequest sendEvaluationRequest);

}
