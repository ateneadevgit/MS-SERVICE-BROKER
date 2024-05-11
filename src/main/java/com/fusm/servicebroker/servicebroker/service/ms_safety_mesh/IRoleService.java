package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.RoleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoleService {

    Object getRoles();
    String createRole(RoleRequest roleRequest);
    String updateRole(RoleRequest roleRequest, Integer roleId);
    void enableDisableRole(Integer roleId, Boolean enabled);
    Object getRolesWithNotPermissionInModule(Integer moduleId);

}
