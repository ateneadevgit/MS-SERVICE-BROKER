package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ModuleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IModuleService {

    Object getModules();
    String createModule(ModuleRequest moduleRequest);
    String updateModule(ModuleRequest moduleRequest, Integer moduleId);

}
