package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.CurriculumSummaryRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewListModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.UpdateCurriculumSummary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ICurriculumSummaryService {

    void createCurruculumSummary(CurriculumSummaryRequest curriculumSummaryRequest);
    Object getCurriculumSummary(Map<String, Object> params, Integer typeCurriculum);
    void updateCurruculumSummary(UpdateCurriculumSummary updateCurriculumSummary, Integer summaryId);
    Object getCurriculumSummaryByProgram(Integer programId, Integer type);

}
