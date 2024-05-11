package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.RenovationSubjectGuideRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.UserData;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IRenovationSubjectGuideService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RenovationSubjectGuideService implements IRenovationSubjectGuideService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createRenovationSubjectGuide(RenovationSubjectGuideRequest renovationSubjectGuideRequest, Integer subjectGuideId) {
        renovationSubjectGuideRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/" + subjectGuideId + "/renovation")
                .bodyValue(renovationSubjectGuideRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void evaluateRenovationSubjectGuide(EvaluateGuide evaluateGuide, Integer renovationId) {
        evaluateGuide.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluateGuide.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/renovation/" + renovationId)
                .bodyValue(evaluateGuide)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getRenovationsBySubjectGuide(Integer subjectGuideId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/" + subjectGuideId + "/renovation/get")
                .bodyValue(
                        UserData.builder()
                                .roleId(globalService.accessGlobalUserData().getRole())
                                .userEmail(globalService.accessGlobalUserData().getEmail())
                                .userId(globalService.accessGlobalUserData().getEmail())
                                .build()
                )
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
