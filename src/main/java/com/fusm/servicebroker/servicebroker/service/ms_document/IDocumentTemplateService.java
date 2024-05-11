package com.fusm.servicebroker.servicebroker.service.ms_document;

import org.springframework.stereotype.Service;

@Service
public interface IDocumentTemplateService {

    String getTemplate(Integer templateId);

}
