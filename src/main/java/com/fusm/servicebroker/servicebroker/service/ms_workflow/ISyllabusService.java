package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SyllabusModel;

import java.io.IOException;

@Service
public interface ISyllabusService {

    String syllabusPdf(Integer curriculumId) throws DocumentException, IOException;
    Object getPreloadInformation(Integer curriculumId);
    void createSyllabusAndComplementaryInformation(SyllabusModel syllabusModel);
    Boolean getSyllabusExist(Integer curriculumId);
    Object getSyllabusByCurriculum(Integer curriculumId);
    void updateSyllabus(Integer syllabusId, SyllabusModel syllabusModel);
}
