package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.model.ms_program.GuidelineRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.SearchModel;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProgramAttachmentService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProgramAttachmentService implements IProgramAttachmentService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getGuidelineAttachment() {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/attachment/guideline")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public Object getGuidelineAttachmentByType(Integer type) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/attachment/guideline/by-type/" + type)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createGuidelineAttachment(GuidelineRequest guidelineRequest) {
        guidelineRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/attachment")
                .bodyValue(guidelineRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateGuidelineAttachment(GuidelineRequest guidelineRequest, Integer guidelineId) {
        guidelineRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .put()
                .uri(PROGRAMS_SERVICE + "/attachment/" + guidelineId)
                .bodyValue(guidelineRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableGuidelineAttachment(Integer guidelineId) {
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .delete()
                .uri(PROGRAMS_SERVICE + "/attachment/" + guidelineId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getMinutes(SearchModel searchModel) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .post()
                .uri(PROGRAMS_SERVICE + "/attachment/minute")
                .bodyValue(searchModel)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void deleteGuidelineAttachment(Integer guidelineId) {
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .delete()
                .uri(PROGRAMS_SERVICE + "/attachment/delete/" + guidelineId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void enableDisableGuideline(Integer guidelineId, Boolean enabled) {
        webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .delete()
                .uri(PROGRAMS_SERVICE + "/attachment/" + guidelineId + "/enable-disable/" + enabled)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String getGuidelineAttachmentById(Integer guidelineId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/attachment/guideline/" + guidelineId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
