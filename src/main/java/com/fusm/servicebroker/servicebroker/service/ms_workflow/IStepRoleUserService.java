package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import org.springframework.stereotype.Service;

@Service
public interface IStepRoleUserService {

    Object getUserRelatedWithProgram(Integer programId, Integer roleId);

}
