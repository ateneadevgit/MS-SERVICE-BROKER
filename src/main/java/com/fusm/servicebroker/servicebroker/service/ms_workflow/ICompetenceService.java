package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.CompetenceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompetenceService {

    void createCompetence(CompetenceRequest competenceRequest);
    Object getCompetences(Boolean isNif);
    void updateCompetence(CompetenceRequest competenceRequest, Integer competendeId);

}
