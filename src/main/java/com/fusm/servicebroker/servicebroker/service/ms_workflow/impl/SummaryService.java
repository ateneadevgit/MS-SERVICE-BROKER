package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewListModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SummaryRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISummaryService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SummaryService implements ISummaryService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createSummary(SummaryRequest summaryRequest) {
        summaryRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        summaryRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/summary")
                .bodyValue(summaryRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getSummary(Map<String, Object> params) {
        Integer workflowId = params.get("workflowId") != null ? Integer.parseInt(params.get("workflowId").toString()) : null;
        Integer stepId = params.get("stepId") != null ? Integer.parseInt(params.get("stepId").toString()) : null;

        if (stepId == null || workflowId == null) throw new GlobalCustomException(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);

        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/summary/get")
                .bodyValue(ReviewListModel.builder()
                        .workflowId(workflowId)
                        .stepId(stepId)
                        .createdBy(globalService.accessGlobalUserData().getEmail())
                        .roleId(globalService.accessGlobalUserData().getRole())
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getSummaryByProgramAndType(Integer programId, Integer type) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/summary/curricular/object-id/" + programId + "/type/" + type)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateSummary(SummaryRequest summaryRequest) {
        summaryRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        summaryRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/summary")
                .bodyValue(summaryRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void sendSummaryToEvaluation(Integer summaryId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/summary/" + summaryId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Boolean hasAlreadyEvaluated(Integer summaryId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/summary/" + summaryId + "/is-evaluated/role-id/" + globalService.accessGlobalUserData().getRole())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    @Override
    public Boolean hasAlreadySendToEvaluation(Integer summaryId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/summary/" + summaryId + "/sended")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

}
