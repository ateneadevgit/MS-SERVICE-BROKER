package com.fusm.servicebroker.servicebroker.service.ms_program;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProgramModuleService {

    Object getProgramModules(Boolean isEnlarge);
    Object getAllProgramModules(Boolean isEnlarge);

}
