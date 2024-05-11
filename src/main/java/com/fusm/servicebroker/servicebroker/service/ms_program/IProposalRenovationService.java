package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fusm.servicebroker.servicebroker.model.ms_program.EvaluateProposalRenovationRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ProposalUpgradeRequest;
import org.springframework.stereotype.Service;

@Service
public interface IProposalRenovationService {

    void createProposalRenovation(ProposalUpgradeRequest proposalUpgradeRequest, Integer programId);
    void evaluateProposalRenovation(EvaluateProposalRenovationRequest evaluateProposalRenovationRequest, Integer programId);
    Object getRequestProposal(Integer programId);
    Object getSelectedModules(Integer programId);
    Object getOnEditionModules(Integer programId);

}
