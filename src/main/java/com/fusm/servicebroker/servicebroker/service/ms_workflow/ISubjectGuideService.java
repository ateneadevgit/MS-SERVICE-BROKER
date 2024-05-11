package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateGuide;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchTeacher;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SubjectGuideRequest;
import org.springframework.stereotype.Service;

@Service
public interface ISubjectGuideService {

    void createSubjectGuide(SubjectGuideRequest subjectGuideRequest, Integer curriculumId);
    void updateSubjectGuide(SubjectGuideRequest subjectGuideRequest, Integer curriculumId);
    Object getSubjectGuide(SearchTeacher searchTeacher, Integer curriculumId);
    String getSubjectGuidePdf(SearchTeacher searchTeacher, Integer curriculumId);
    Object getPreloadData(Integer curriculumId);
    void evaluateSubejctGuide(EvaluateGuide evaluateGuide, Integer subjectGuideId);


}
