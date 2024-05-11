package com.fusm.servicebroker.servicebroker.service.ms_document.impl;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentRequest;
import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentResponse;
import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentRoute;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_document.IDocumentManagerService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DocumentManagerService implements IDocumentManagerService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-document.complete-path}")
    private String DOCUMENT_ROUTE;

    @Value("${ms-document.document.path}")
    private String DOCUMENT_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public DocumentResponse exportFile(DocumentRoute documentRoute) {
        return webClientConnector.connectWebClient(DOCUMENT_ROUTE)
                .post()
                .uri(DOCUMENT_SERVICE + "/export")
                .bodyValue(documentRoute)
                .retrieve()
                .bodyToMono(DocumentResponse.class)
                .block();
    }

    @Override
    public String saveDocument(DocumentRequest documentRequest) {
        documentRequest.setDocumentVersion("1");
        documentRequest.setIdUser(globalService.accessGlobalUserData().getEmail());
        return webClientConnector.connectWebClient(DOCUMENT_ROUTE)
                .post()
                .uri(DOCUMENT_SERVICE)
                .bodyValue(documentRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
