package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.SecondLanguageRequest;
import org.springframework.stereotype.Service;

@Service
public interface ISecondLanguageService {

    void createSecondLanguage(SecondLanguageRequest secondLanguageRequest);
    void updateSecondLanguage(SecondLanguageRequest secondLanguageRequest, Integer secondLanguageId);
    void disableSecondLanguage(Integer secondLanguageId);
    Object getSecondLanguages();
    Object getSecondLanguagesByGroup();
    Object getSecondLanguageById(Integer secondLanguageId);

}