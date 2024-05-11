package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.RenovationSubjectGuideRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.UserData;
import org.springframework.stereotype.Service;

@Service
public interface IRenovationSubjectGuideService {

    void createRenovationSubjectGuide(RenovationSubjectGuideRequest renovationSubjectGuideRequest, Integer subjectGuideId);
    void evaluateRenovationSubjectGuide(EvaluateGuide evaluateGuide, Integer renovationId);
    Object getRenovationsBySubjectGuide(Integer subjectGuideId);

}
