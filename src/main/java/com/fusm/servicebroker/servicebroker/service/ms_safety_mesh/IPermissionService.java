package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.PermissionRequest;
import org.springframework.stereotype.Service;


@Service
public interface IPermissionService {

    Object getPermissionByRole(Integer roleId);
    void createPermission(PermissionRequest permissionRequest);
    void updatePermission(PermissionRequest permissionRequest);
    Object getModuleByRole(Integer roleId);
    Object getFloatingModuleByRole(Integer roleId);
    Object getPermissionsByModule(Integer moduleId);

}
