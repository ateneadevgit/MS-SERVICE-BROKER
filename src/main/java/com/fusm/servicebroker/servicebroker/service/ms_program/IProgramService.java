package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fusm.servicebroker.servicebroker.model.ResponsePage;
import com.fusm.servicebroker.servicebroker.model.ms_program.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface IProgramService {

    Object getProgram();
    Object getProgramsByFaculty(List<Integer> facultyId);
    Object getProgramByFaculty(Integer facultyId);
    Object getTecnicalPrograms(Integer facultyId);
    String createProposal(ProgramRequestModel programRequestModel);
    String updateProposal(ProgramUpdateModel programUpdateModel, Integer proposalId);
    Object getProposal(Map<String, Object> params);
    Object getProgramByStatus(Map<String, Object> params);
    Object getProgramById(Integer programId);
    Object getProposalHistoric(Integer proposalId);
    boolean getIsEnlarge(Integer programId);
    Integer getUpdateStatus(Integer programId);
    Date getApprovedProposalDate(Integer programId);
    Object getProgramAcademic(QueryFilterProposal queryFilterProposal);

}
