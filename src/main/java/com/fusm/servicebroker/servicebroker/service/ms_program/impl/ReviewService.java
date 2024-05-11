package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.model.ms_program.ReviewGeneralRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.GetReviewModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_program.IReviewService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createReview(ReviewGeneralRequest reviewRequest) {
        reviewRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        reviewRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/review")
                .bodyValue(reviewRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getReviews(GetReviewModel getReviewModel) {
        getReviewModel.setUserId(globalService.accessGlobalUserData().getEmail());
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/review/get")
                .bodyValue(getReviewModel)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
