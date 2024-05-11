package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.EvaluateProposalModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ProblemBankRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SearchModelProblem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProblemBankService {

    void createProblemBank(ProblemBankRequest problemBankRequest);

    Object getNifProblemBank(SearchModelProblem searchModel);
    Object getProblemBank(SearchModelProblem searchModel);

    void updateProblemBank(ProblemBankRequest problemBankRequest, Integer problemBankId);

    void evaluateProblemBank(EvaluateProposalModel evaluateProposalModel, Integer problemBankId);
    void enableDisableProblemBank(Integer problemBankId, Boolean enabled);

}