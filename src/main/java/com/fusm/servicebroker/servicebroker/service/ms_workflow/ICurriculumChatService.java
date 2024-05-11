package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.CurriculumChatRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.GetReviewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICurriculumChatService {

    void createReview(CurriculumChatRequest curriculumChatRequest);
    Object getReviews(GetReviewModel getReviewModel);
    public void readCurriculumChat(List<Integer> curriculumChats);

}
