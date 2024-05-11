package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.model.ms_program.TraceabilityRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_program.ITraceabilityService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TraceabilityService implements ITraceabilityService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String TRACEABILITY_ROUTE;

    @Value("${ms-program.trace.path}")
    private String TRACEABILITY_SERVICE;

    @Autowired
    private GlobalService globalService;

    @Override
    public Object getProgramTraceability(Integer programId) {
        return webClientConnector.connectWebClient(TRACEABILITY_ROUTE)
                .get()
                .uri(TRACEABILITY_SERVICE + "/program-id/" + programId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createTraceability(TraceabilityRequest traceabilityRequest, Integer programId) {
        traceabilityRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        traceabilityRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(TRACEABILITY_ROUTE)
                .post()
                .uri(TRACEABILITY_SERVICE + "/program-id/" + programId)
                .bodyValue(traceabilityRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateTraceability(TraceabilityRequest traceabilityRequest, Integer programId) {
        traceabilityRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        traceabilityRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(TRACEABILITY_ROUTE)
                .put()
                .uri(TRACEABILITY_SERVICE + "/program-id/" + programId)
                .bodyValue(traceabilityRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
