package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.TemplateRequest;
import org.springframework.stereotype.Service;

@Service
public interface IStepTemplateService {

    void createTemplate(TemplateRequest templateRequest);
    void updateTemplate(TemplateRequest templateRequest, Integer templateId);

}

