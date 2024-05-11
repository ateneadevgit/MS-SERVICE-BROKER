package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IStepRoleUserService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con las acciones que puede realizar un usuario segpun un paso
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/user")
public class StepRoleUserController {

    @Autowired
    private IStepRoleUserService stepRoleUserService;

    /**
     * Obtiene los programas a los que tiene acceso un rol
     * @param objectId ID del programa
     * @param roleId ID del rol
     * @return lista de usuarios asignados al programa
     */
    @GetMapping("/object-id/{objectId}/role-id/{id}")
    public ResponseEntity<Response<Object>> getUserRelatedWithProgram(
            @PathVariable("objectId") Integer objectId,
            @PathVariable("id") Integer roleId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, stepRoleUserService.getUserRelatedWithProgram(objectId, roleId))
        );
    }

}

