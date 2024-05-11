package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.LearningAssessmentRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ILearningAssessmentService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LearningAssessmentService implements ILearningAssessmentService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createLearningAssessment(LearningAssessmentRequest<FileModel> learningAssessmentRequest, Integer curriculumId) {
        learningAssessmentRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/learning/curriculum-id/" + curriculumId)
                .bodyValue(learningAssessmentRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateLearningAssessment(LearningAssessmentRequest<FileModel> learningAssessmentRequest, Integer learningAssessmentId) {
        learningAssessmentRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/learning/" + learningAssessmentId)
                .bodyValue(learningAssessmentRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getLearningAssessmentByCurriculumId(Integer curriculumId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/learning/curriculum-id/" + curriculumId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void disableLearningAssessment(Integer learningAssessmentId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/learning/" + learningAssessmentId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
