package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ICurriculumService {

    void createCurriculum(CurriculumListRequest curriculumRequest);
    Object getCurriculum(Integer objectId);
    void updateCurriculum(UpdateCurriculumRequest curriculumRequest, Integer curriculumId);
    void disableCurriculum(Integer curriculumId);
    Object getCurriculumByType(Integer programId, Integer type);
    Object getCurriculumByFather(Integer programId, Integer fatherId);
    void calculateParticipationPercentage(Integer programId);
    Object getSubjects(Integer programId);
    Integer getCurriculumCredits(Integer objectId);
    Object getCurriculumSemesterByProgram(Integer programId);
    Object getCurriculumDetailById(Integer curriculumId);
    String curriculumPdf(Integer programId);
    void createNifsCurriculum(SubcoreNifsRequest subcoreNifsRequest);
    Object getNifsCurriculum();
    Object getSemestersByProgram(Integer programId);
    Object getProgramSubject(SearchSubject searchSubject, PageModel pageModel);
    Object getTeachersBySubject(Integer subjectId);
    void updateComplementaryNifs(UpdateComplementaryNifs complementaryNifs, Integer curriculumId);
    Object getLevelsByProgram(Integer programId);
    void createComplementaryEvaluation(ComplementaryEvaluationRequest complementaryEvaluationRequest, Integer curriculumId);
    void updateComplementaryEvaluation(ComplementaryEvaluationRequest complementaryEvaluationRequest, Integer curriculumId);
    Object getCurriculumEvaluationByType(Integer programId, Integer type);
    Object getProgramProgress(Integer programId);
    Object getHistorySubject(Integer programId);
    Object getCurrentSubject(Integer programId);


}
