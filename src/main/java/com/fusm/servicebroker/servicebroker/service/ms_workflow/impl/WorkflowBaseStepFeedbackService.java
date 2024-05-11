package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateStepRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateTraceability;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseStepFeedbackService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WorkflowBaseStepFeedbackService implements IWorkflowBaseStepFeedbackService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void evaluateStep(EvaluateStepRequest evaluateStepRequest) {
        evaluateStepRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        evaluateStepRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/feedback")
                .bodyValue(evaluateStepRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void evaluateTraceability(EvaluateTraceability evaluateTraceability, Integer programId) {
        evaluateTraceability.setRoleId(globalService.accessGlobalUserData().getRole());
        evaluateTraceability.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/feedback/traceability/program-id/" + programId)
                .bodyValue(evaluateTraceability)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
