package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ColumnRequest;
import com.fusm.servicebroker.servicebroker.service.ms_safety_mesh.IColumnService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ColumnService implements IColumnService {

    @Autowired
    private IMiddlewareService middlewareService;

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-safety-mesh.complete-path}")
    private String COLUMN_ROUTE;

    @Value("${ms-safety-mesh.column.path}")
    private String COLUMN_SERVICE;


    @Override
    public Object getColumns() {
        return webClientConnector.connectWebClient(COLUMN_ROUTE)
                .get()
                .uri(COLUMN_SERVICE)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public String createColumn(ColumnRequest columnRequest) {
        return webClientConnector.connectWebClient(COLUMN_ROUTE)
                .post()
                .uri(COLUMN_SERVICE)
                .bodyValue(columnRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String updateColumn(ColumnRequest columnRequest, Integer columnId) {
        return webClientConnector.connectWebClient(COLUMN_ROUTE)
                .put()
                .uri(COLUMN_SERVICE + "/" + columnId)
                .bodyValue(columnRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
