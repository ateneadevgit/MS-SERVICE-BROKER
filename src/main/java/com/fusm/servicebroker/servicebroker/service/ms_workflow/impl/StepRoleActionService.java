package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepRoleActionRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IStepRoleActionService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StepRoleActionService implements IStepRoleActionService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getRolesRelatedWithStep(Integer stepId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/step-role-action/step-id/" + stepId + "/roles")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void deleteRoleFromStep(Integer roleId, Integer stepId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/step-role-action/step-id/" + stepId + "/role-id/" + roleId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void addRoleActionToStep(Integer stepId, StepRoleActionRequest roleActions) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step-role-action/step-id/" + stepId)
                .bodyValue(roleActions)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateActionToRole(Integer stepId, StepRoleActionRequest roleActions) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/step-role-action/step-id/" + stepId)
                .bodyValue(roleActions)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
