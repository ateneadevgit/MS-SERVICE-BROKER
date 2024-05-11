package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewListModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IWorkflowBaseStepReviewService {

    void createReview(ReviewRequest reviewRequest);
    Object getReviewByStep(Map<String, Object> params);

}
