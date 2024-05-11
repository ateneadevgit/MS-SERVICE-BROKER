package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fusm.servicebroker.servicebroker.model.ms_program.ReviewGeneralRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.GetReviewModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewRequest;
import org.springframework.stereotype.Service;

@Service
public interface IReviewService {

    void createReview(ReviewGeneralRequest reviewRequest);
    Object getReviews(GetReviewModel getReviewModel);

}