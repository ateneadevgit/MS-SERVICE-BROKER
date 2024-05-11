package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.LearningAssessmentRequest;
import org.springframework.stereotype.Service;

@Service
public interface ILearningAssessmentService {

    void createLearningAssessment(LearningAssessmentRequest<FileModel> learningAssessmentRequest, Integer curriculumId);
    void updateLearningAssessment(LearningAssessmentRequest<FileModel> learningAssessmentRequest, Integer learningAssessmentId);
    Object getLearningAssessmentByCurriculumId(Integer curriculumId);
    void disableLearningAssessment(Integer learningAssessmentId);

}
