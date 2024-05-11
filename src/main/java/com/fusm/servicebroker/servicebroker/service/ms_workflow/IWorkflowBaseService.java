package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.UserData;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowBaseRequest;
import org.springframework.stereotype.Service;

@Service
public interface IWorkflowBaseService {

    void createWorkflowBase(WorkflowBaseRequest workflowBaseRequest);
    Boolean hasFlowStarted(Integer programId);
    void relateUserToWorkflow(UserData userData, Integer programId);
    void relateUserToWorkflowFather(UserData userData, Integer programId);

}
