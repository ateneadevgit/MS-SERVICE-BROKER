package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.CompetenceRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICompetenceService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompetenceService implements ICompetenceService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createCompetence(CompetenceRequest competenceRequest) {
        competenceRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        competenceRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/competences")
                .bodyValue(competenceRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCompetences(Boolean isNif) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/competences/is-nif/" + isNif)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateCompetence(CompetenceRequest competenceRequest, Integer competendeId) {
        competenceRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/competences/" + competendeId)
                .bodyValue(competenceRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
