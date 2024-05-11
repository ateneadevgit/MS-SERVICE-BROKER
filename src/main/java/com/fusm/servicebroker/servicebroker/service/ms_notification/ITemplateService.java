package com.fusm.servicebroker.servicebroker.service.ms_notification;

import com.fusm.servicebroker.servicebroker.model.ms_notification.TemplateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITemplateService {

    Object getTemplates();
    void updateTemplate(TemplateRequest templateRequest, Integer templateId);
    Object getTemplate(Integer templateId);

}
