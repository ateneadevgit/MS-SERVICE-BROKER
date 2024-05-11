package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISyllabusService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import com.itextpdf.text.DocumentException;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SyllabusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Service
public class SyllabusService implements ISyllabusService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public String syllabusPdf(Integer curriculumId) throws DocumentException, IOException {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/syllabus/curriculum-id/" + curriculumId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getPreloadInformation(Integer curriculumId) {

        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/syllabus/preload-information/" + curriculumId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createSyllabusAndComplementaryInformation(SyllabusModel syllabusModel) {
        syllabusModel.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/syllabus")
                .bodyValue(syllabusModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Boolean getSyllabusExist(Integer curriculumId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/syllabus/exist/curriculum-id/" + curriculumId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    @Override
    public Object getSyllabusByCurriculum(Integer curriculumId) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/syllabus/data/curriculum-id/" + curriculumId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateSyllabus(Integer syllabusId, SyllabusModel syllabusModel) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/syllabus/" + syllabusId )
                .bodyValue(syllabusModel)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
