package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.UserData;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowBaseRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowStepRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WorkflowBaseService implements IWorkflowBaseService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createWorkflowBase(WorkflowBaseRequest workflowBaseRequest) {
        workflowBaseRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        workflowBaseRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/base")
                .bodyValue(workflowBaseRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Boolean hasFlowStarted(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/base/started/" + programId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    @Override
    public void relateUserToWorkflow(UserData userData, Integer programId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/base/relate-user/object-id/" + programId)
                .bodyValue(userData)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void relateUserToWorkflowFather(UserData userData, Integer programId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/base/relate-user/father/object-id/" + programId)
                .bodyValue(userData)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
