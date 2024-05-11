package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateProposalModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.WorkflowRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkFlowService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WorkFlowService implements IWorkFlowService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public String evaluateProposal(EvaluateProposalModel evaluation, Integer proposalId) {
        evaluation.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluation.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/proposal/" + proposalId)
                .bodyValue(evaluation)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String declineOrDisableProgram(EvaluateProposalModel evaluation, Integer programId) {
        evaluation.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluation.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/decline-disable/program/" + programId)
                .bodyValue(evaluation)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getWorkflows() {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/workflow")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateWorkflow(WorkflowRequest workflowRequest, Integer workflowId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/workflow/" + workflowId)
                .bodyValue(workflowRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
