package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.PastActivityRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchTeacher;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SubjectActivityRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISubjectActivityService {

    void createSubjectActivity(List<SubjectActivityRequest> activityRequest, Integer subjectGuideId);
    void updateSubjectActivity(SubjectActivityRequest activityRequest, Integer subjectActivityId);
    void deleteSubjectActivity(Integer subjectActivityId);
    Object getPastActivities(SearchTeacher searchTeacher, Integer subjectGuideId);
    void addPastActivityToCurrentPeriod(List<PastActivityRequest> pastActivityRequests);
    void evaluateSubjectActivity(EvaluateGuide evaluateGuide, Integer subjectGuideId);

}

