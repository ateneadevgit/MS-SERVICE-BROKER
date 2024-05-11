package com.fusm.servicebroker.servicebroker.service.ms_notification.impl;

import com.fusm.servicebroker.servicebroker.model.ms_notification.TemplateRequest;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_notification.ITemplateService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TemplateService implements ITemplateService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-notification.complete-path}")
    private String NOTIFICATION_ROUTE;

    @Value("${ms-notification.template.path}")
    private String TEMPLATE_SERVICE;


    @Override
    public Object getTemplates() {
        return webClientConnector.connectWebClient(NOTIFICATION_ROUTE)
                .get()
                .uri(TEMPLATE_SERVICE)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateTemplate(TemplateRequest templateRequest, Integer templateId) {
        webClientConnector.connectWebClient(NOTIFICATION_ROUTE)
                .put()
                .uri(TEMPLATE_SERVICE + "/" + templateId)
                .bodyValue(templateRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getTemplate(Integer templateId) {
        return webClientConnector.connectWebClient(NOTIFICATION_ROUTE)
                .get()
                .uri(TEMPLATE_SERVICE + "/" + templateId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
