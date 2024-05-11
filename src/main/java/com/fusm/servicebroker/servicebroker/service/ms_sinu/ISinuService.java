package com.fusm.servicebroker.servicebroker.service.ms_sinu;

import com.fusm.servicebroker.servicebroker.model.ms_sinu.SearchDirectory;
import com.fusm.servicebroker.servicebroker.model.ms_sinu.SearchRoles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISinuService {

    Object getUsersByRole(Integer roleId);
    Object getUserByRolesIds(SearchRoles roles);
    Object getDirectory(SearchDirectory searchDirectory);
    Object getUserGender();
    Integer getStudentsByFaculty(Integer facultyId);

}
