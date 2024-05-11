package com.fusm.servicebroker.servicebroker.service.ms_workflow.impl;

import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ActionService implements IActionService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-workflow.complete-path}")
    private String WORKFLOW_ROUTE;

    @Value("${ms-workflow.workflow.path}")
    private String WORKFLOW_SERVICE;


    @Override
    public Object getActions() {
        return webClientConnector.connectWebClient(WORKFLOW_ROUTE)
                .get()
                .uri(WORKFLOW_SERVICE + "/action")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
