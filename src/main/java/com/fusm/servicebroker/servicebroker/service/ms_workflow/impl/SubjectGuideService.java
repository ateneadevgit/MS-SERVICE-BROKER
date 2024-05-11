package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchTeacher;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SubjectGuideRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISubjectGuideService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubjectGuideService implements ISubjectGuideService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createSubjectGuide(SubjectGuideRequest subjectGuideRequest, Integer curriculumId) {
        subjectGuideRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        subjectGuideRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/curriculum-id/" + curriculumId)
                .bodyValue(subjectGuideRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateSubjectGuide(SubjectGuideRequest subjectGuideRequest, Integer curriculumId) {
        subjectGuideRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        subjectGuideRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/guide/curriculum-id/" + curriculumId)
                .bodyValue(subjectGuideRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getSubjectGuide(SearchTeacher searchTeacher, Integer curriculumId) {
        searchTeacher.setRoleId(globalService.accessGlobalUserData().getRole());
        searchTeacher.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/curriculum-id/" + curriculumId + "/data")
                .bodyValue(searchTeacher)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public String getSubjectGuidePdf(SearchTeacher searchTeacher, Integer curriculumId) {
        searchTeacher.setRoleId(globalService.accessGlobalUserData().getRole());
        searchTeacher.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/pdf/curriculum-id/" + curriculumId)
                .bodyValue(searchTeacher)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getPreloadData(Integer curriculumId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/guide/pre-load/curriculum-id/" + curriculumId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void evaluateSubejctGuide(EvaluateGuide evaluateGuide, Integer subjectGuideId) {
        evaluateGuide.setRoleId(globalService.accessGlobalUserData().getRole());
        evaluateGuide.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/" + subjectGuideId + "/evaluate")
                .bodyValue(evaluateGuide)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
