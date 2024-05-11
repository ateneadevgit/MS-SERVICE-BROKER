package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.servicebroker.servicebroker.model.ms_program.EvaluateObjectRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ObjectTempRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ObjectToReview;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_program.IObjectTempService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ObjectTempService implements IObjectTempService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createdObjectTemp(ObjectTempRequest objectTempRequest, Integer programId) throws JsonProcessingException {
        objectTempRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        objectTempRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/object/program/" + programId)
                .bodyValue(objectTempRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void evaluateObject(EvaluateObjectRequest evaluateObjectRequest, Integer programId) throws JsonProcessingException {
        evaluateObjectRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        evaluateObjectRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/object/evaluate/program/" + programId)
                .bodyValue(evaluateObjectRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void sendObjectToReview(ObjectToReview objectToReview) {
        objectToReview.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        objectToReview.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/object/send-review")
                .bodyValue(objectToReview)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getObjectTemp(Integer programId, Integer moduleId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/object/module-id/" + moduleId + "/program-id/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateObjectTemp(ObjectTempRequest objectTempRequest, Integer objectId) {
        objectTempRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        objectTempRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .put()
                .uri(PROGRAMS_SERVICE + "/object/program-id/" + objectId)
                .bodyValue(objectTempRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Integer getObjectNumberCredits(Integer moduleId, Integer programId) throws JsonProcessingException {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/object/module-id/" + moduleId + "/program-id/" + programId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    @Override
    public Object getCores(Integer moduleId, Integer programId) throws JsonProcessingException {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/object/module-id/" + moduleId + "/program-id/" + programId + "/core")
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

    @Override
    public Object getSubjectsByCore(Integer coreId, Integer moduleId, Integer programId) throws JsonProcessingException {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/object/module-id/" + moduleId + "/program-id/" + programId + "/by-core/" + coreId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

}
