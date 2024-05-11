package com.fusm.servicebroker.servicebroker.service.ms_document.impl;

import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentResponse;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_document.IDocumentTemplateService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DocumentTemplateService implements IDocumentTemplateService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-document.complete-path}")
    private String DOCUMENT_ROUTE;

    @Value("${ms-document.document.path}")
    private String DOCUMENT_SERVICE;


    @Override
    public String getTemplate(Integer templateId) {
        return webClientConnector.connectWebClient(DOCUMENT_ROUTE)
                .get()
                .uri(DOCUMENT_SERVICE + "/pdf/" + templateId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
