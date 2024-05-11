package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;


import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.*;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CurriculumService implements ICurriculumService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createCurriculum(CurriculumListRequest curriculumRequest) {
        curriculumRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        curriculumRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum")
                .bodyValue(curriculumRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCurriculum(Integer objectId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + objectId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateCurriculum(UpdateCurriculumRequest curriculumRequest, Integer curriculumId) {
        curriculumRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        curriculumRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + curriculumId)
                .bodyValue(curriculumRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableCurriculum(Integer curriculumId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + curriculumId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCurriculumByType(Integer programId, Integer type) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/by-type/" + type)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getCurriculumByFather(Integer programId, Integer fatherId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/by-father/" + fatherId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void calculateParticipationPercentage(Integer programId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/calculate/percentage")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getSubjects(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/subjects")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Integer getCurriculumCredits(Integer objectId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/credits/object-id/" + objectId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    @Override
    public Object getCurriculumSemesterByProgram(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/semester/object-id/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getCurriculumDetailById(Integer curriculumId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/detail/" + curriculumId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public String curriculumPdf(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/pdf/object-id/" + programId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void createNifsCurriculum(SubcoreNifsRequest subcoreNifsRequest) {
        subcoreNifsRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        subcoreNifsRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        subcoreNifsRequest.getSubjectRequest().setCreatedBy(globalService.accessGlobalUserData().getEmail());
        subcoreNifsRequest.getSubjectRequest().setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/nif")
                .bodyValue(subcoreNifsRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getNifsCurriculum() {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/nif")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getSemestersByProgram(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/semester-number/object-id/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getProgramSubject(SearchSubject searchSubject, PageModel pageModel) {
        searchSubject.setUserId(globalService.accessGlobalUserData().getEmail());
        searchSubject.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        searchSubject.setRoleId(globalService.accessGlobalUserData().getRole());
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/program-subject?size=" + pageModel.getSize() + "&page=" + pageModel.getPage())
                .bodyValue(searchSubject)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getTeachersBySubject(Integer subjectId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + subjectId + "/teacher/user-id/" + globalService.accessGlobalUserData().getEmail() + "/role-id/" + globalService.accessGlobalUserData().getRole())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateComplementaryNifs(UpdateComplementaryNifs complementaryNifs, Integer curriculumId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + curriculumId + "/complementary-nif")
                .bodyValue(complementaryNifs)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getLevelsByProgram(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/levels/object-id/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createComplementaryEvaluation(ComplementaryEvaluationRequest complementaryEvaluationRequest, Integer curriculumId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + curriculumId + "/complementary-evaluation")
                .bodyValue(complementaryEvaluationRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateComplementaryEvaluation(ComplementaryEvaluationRequest complementaryEvaluationRequest, Integer curriculumId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/curriculum/" + curriculumId + "/complementary-evaluation")
                .bodyValue(complementaryEvaluationRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getCurriculumEvaluationByType(Integer programId, Integer type) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/by-type/" + type + "/complementary-evaluation")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getProgramProgress(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/user-id/" + globalService.accessGlobalUserData().getEmail())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getHistorySubject(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/historic/user-id/" + globalService.accessGlobalUserData().getEmail())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getCurrentSubject(Integer programId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/curriculum/object-id/" + programId + "/current/user-id/" + globalService.accessGlobalUserData().getEmail())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
