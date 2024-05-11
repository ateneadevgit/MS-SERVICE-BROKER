package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.NifsRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.INifsService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NifsService implements INifsService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createNifsData(NifsRequest nifsRequest) {
        nifsRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/nif")
                .bodyValue(nifsRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateNifsData(NifsRequest nifsRequest, Integer nifId) {
        nifsRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .put()
                .uri(WORKFLOW_SERVICE + "/nif/" + nifId)
                .bodyValue(nifsRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void addSection(List<NifsRequest> nifsRequest, Integer nifId) {
        for (NifsRequest request : nifsRequest) request.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .post()
                .uri(WORKFLOW_SERVICE + "/nif/" + nifId)
                .bodyValue(nifsRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableSection(Integer nifId) {
        webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .delete()
                .uri(WORKFLOW_SERVICE + "/nif/" + nifId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object viewNifsData(Integer type) {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/nif/type/" + type)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
