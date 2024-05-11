package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewListModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SummaryRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ISummaryService {

    void createSummary(SummaryRequest summaryRequest);
    Object getSummary(Map<String, Object> params);
    Object getSummaryByProgramAndType(Integer programId, Integer type);
    void updateSummary(SummaryRequest summaryRequest);
    void sendSummaryToEvaluation(Integer summaryId);
    Boolean hasAlreadyEvaluated(Integer summaryId);
    Boolean hasAlreadySendToEvaluation(Integer summaryId);

}
