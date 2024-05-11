package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IStepService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StepService implements IStepService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createStep(StepRequest stepRequest, Integer workflowId) {
        stepRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/workflow-id/" + workflowId)
                .bodyValue(stepRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void enableDisableStep(Integer stepId, Integer workflowId, Boolean enabled) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/step/" + stepId + "/workflow-id/" + workflowId + "/enable/" + enabled)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getStepById(Integer stepId, Integer workflowId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/step/" + stepId + "/workflow-id/" + workflowId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateStep(StepRequest stepRequest, Integer workflowId, Integer stepId) {
        stepRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/step/" + stepId + "/workflow-id/" + workflowId)
                .bodyValue(stepRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
