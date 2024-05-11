package com.fusm.servicebroker.servicebroker.service.ms_program.impl;

import com.fusm.servicebroker.servicebroker.service.ms_program.IHistoryService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-program.complete-path}")
    private String PROGRAM_ROUTE;

    @Value("${ms-program.programs.path}")
    private String PROGRAMS_SERVICE;


    @Override
    public Object getHistorySpecific(Integer programId, Integer moduleId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/history/program-id/" + programId + "/module-id/" + moduleId)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

    @Override
    public Object gitHistoricByType(Integer programId, Integer moduleId, Integer typeId) {
        return webClientConnector.connectWebClient(PROGRAM_ROUTE)
                .get()
                .uri(PROGRAMS_SERVICE + "/history/program-id/" + programId + "/module-id/" + moduleId + "/type/" + typeId)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
    }

}
