package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepRequest;
import org.springframework.stereotype.Service;

@Service
public interface IStepService {

    void createStep(StepRequest stepRequest, Integer workflowId);
    void enableDisableStep(Integer stepId, Integer workflowId, Boolean enabled);
    Object getStepById(Integer stepId, Integer workflowId);
    void updateStep(StepRequest stepRequest, Integer workflowId, Integer stepId);

}
