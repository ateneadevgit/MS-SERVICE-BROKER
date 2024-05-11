package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.SendEvaluationRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepAttachRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowStepRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseStepService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WorkflowBaseStepService implements IWorkflowBaseStepService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getStepsOfWorkflowByRole(Integer objectId, String workflowType) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/by-object/" + objectId)
                .bodyValue(
                        WorkflowStepRequest.builder()
                                .workflowType(workflowType.toLowerCase())
                                .roleId(globalService.accessGlobalUserData().getRole())
                                .createdBy(globalService.accessGlobalUserData().getEmail())
                                .build()
                )
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void loadAttachment(StepAttachRequest stepAttachRequest) {
        stepAttachRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        stepAttachRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/attach")
                .bodyValue(stepAttachRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableAttachment(Integer programId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/step/attach/" + programId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void sendStepToEvaluation(SendEvaluationRequest sendEvaluationRequest) {
        sendEvaluationRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        sendEvaluationRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/send/evaluation")
                .bodyValue(sendEvaluationRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
