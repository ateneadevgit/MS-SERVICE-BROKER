package com.fusm.servicebroker.servicebroker.service.ms_document;

import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentRequest;
import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentResponse;
import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentRoute;
import org.springframework.stereotype.Service;

@Service
public interface IDocumentManagerService {

    DocumentResponse exportFile(DocumentRoute documentRoute);
    String saveDocument(DocumentRequest documentRequest);

}
