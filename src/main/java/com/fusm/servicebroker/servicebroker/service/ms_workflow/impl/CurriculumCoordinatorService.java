package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.AssignCoordinator;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumCoordinatorService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurriculumCoordinatorService implements ICurriculumCoordinatorService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void assignCoordinator(AssignCoordinator assignCoordinator, Integer subjectId) {
        assignCoordinator.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + subjectId + "/coordinator")
                .bodyValue(assignCoordinator)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object coordinatorAssigned(Integer subjectId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + subjectId + "/coordinator")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
