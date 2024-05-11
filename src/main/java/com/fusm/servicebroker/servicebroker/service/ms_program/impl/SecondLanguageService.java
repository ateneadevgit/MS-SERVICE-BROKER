package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.SecondLanguageRequest;
import com.fusm.servicebroker.servicebroker.service.ms_program.ISecondLanguageService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecondLanguageService implements ISecondLanguageService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;


    @Override
    public void createSecondLanguage(SecondLanguageRequest secondLanguageRequest) {
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/second-language")
                .bodyValue(secondLanguageRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateSecondLanguage(SecondLanguageRequest secondLanguageRequest, Integer secondLanguageId) {
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .put()
                .uri(PROGRAMS_SERVICE + "/second-language/" + secondLanguageId)
                .bodyValue(secondLanguageRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableSecondLanguage(Integer secondLanguageId) {
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .delete()
                .uri(PROGRAMS_SERVICE + "/second-language/" + secondLanguageId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getSecondLanguages() {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/second-language")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getSecondLanguagesByGroup() {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/second-language/group")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getSecondLanguageById(Integer secondLanguageId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/second-language/" + secondLanguageId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
