package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.PastActivityRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchTeacher;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SubjectActivityRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISubjectActivityService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectActivityService implements ISubjectActivityService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createSubjectActivity(List<SubjectActivityRequest> activityRequest, Integer subjectGuideId) {
        for (SubjectActivityRequest subjectActivityRequest : activityRequest)
            subjectActivityRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/" + subjectGuideId + "/activity")
                .bodyValue(activityRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateSubjectActivity(SubjectActivityRequest activityRequest, Integer subjectActivityId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/guide/activity/" + subjectActivityId)
                .bodyValue(activityRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void deleteSubjectActivity(Integer subjectActivityId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/guide/activity/" + subjectActivityId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getPastActivities(SearchTeacher searchTeacher, Integer subjectGuideId) {
        searchTeacher.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        searchTeacher.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/" + subjectGuideId + "/activity/past")
                .bodyValue(searchTeacher)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void addPastActivityToCurrentPeriod(List<PastActivityRequest> pastActivityRequests) {
        pastActivityRequests.forEach(subjectActivityRequest ->
                subjectActivityRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail()));
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/activity/past")
                .bodyValue(pastActivityRequests)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void evaluateSubjectActivity(EvaluateGuide evaluateGuide, Integer subjectGuideId) {
        evaluateGuide.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluateGuide.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/guide/" + subjectGuideId + "/activity/evaluate")
                .bodyValue(evaluateGuide)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
