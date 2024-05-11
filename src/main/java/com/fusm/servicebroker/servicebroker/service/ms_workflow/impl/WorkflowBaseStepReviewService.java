package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewListModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseStepReviewService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WorkflowBaseStepReviewService implements IWorkflowBaseStepReviewService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createReview(ReviewRequest reviewRequest) {
        reviewRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        reviewRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/review")
                .bodyValue(reviewRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getReviewByStep(Map<String, Object> params) {
        Integer workflowId = params.get("workflowId") != null ? Integer.parseInt(params.get("workflowId").toString()) : null;
        Integer stepId = params.get("stepId") != null ? Integer.parseInt(params.get("stepId").toString()) : null;

        if (stepId == null || workflowId == null) throw new GlobalCustomException(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);

        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/step/review/get")
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

}
