package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.CurriculumChatRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.GetReviewModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumChatService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurriculumChatService implements ICurriculumChatService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;

    @Override
    public void createReview(CurriculumChatRequest curriculumChatRequest) {
        curriculumChatRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        curriculumChatRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/chat")
                .bodyValue(curriculumChatRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getReviews(GetReviewModel getReviewModel) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/chat/get")
                .bodyValue(getReviewModel)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }


    @Override
    public void readCurriculumChat(List<Integer> curriculumChats) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/chat/read")
                .bodyValue(curriculumChats)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
