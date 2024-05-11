package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepRoleActionRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IStepRoleActionService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con los permisos de un rol sobre un paso en la creación de un
 * programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/step-role-action")
public class StepRoleActionController {

    @Autowired
    private IStepRoleActionService stepRoleActionService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los roles relacionados con un paso
     * @param stepId ID del paso
     * @return lista de roles
     */
    @GetMapping("/step-id/{id}/roles")
    private ResponseEntity<Response<Object>> getRolesRelatedWithStep(
            @PathVariable("id") Integer stepId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, stepRoleActionService.getRolesRelatedWithStep(stepId))
        );
    }

    /**
     * Elimina un rol dentro de un paso
     * @param stepId ID del paso
     * @param roleId ID del rol
     * @return OK
     */
    @DeleteMapping("/step-id/{id}/role-id/{role-id}")
    private ResponseEntity<Response<String>> deleteRoleFromStep(
            @PathVariable("id") Integer stepId,
            @PathVariable("role-id") Integer roleId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepRoleActionService.deleteRoleFromStep(roleId, stepId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Agregar un rol con acciones a un paso
     * @param stepId ID del paso
     * @param stepRoleActionRequest Modelo que contiene la información a crear
     * @return OK
     */
    @PostMapping("/step-id/{id}")
    private ResponseEntity<Response<String>> addRoleActionToStep(
            @PathVariable("id") Integer stepId,
            @RequestBody StepRoleActionRequest stepRoleActionRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(stepRoleActionRequest.toString(), Constant.POST_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepRoleActionService.addRoleActionToStep(stepId, stepRoleActionRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza las acciones de un rol sobre un paso
     * @param stepId ID del paso
     * @param stepRoleActionRequest Modelo que contiene la información de las acciones
     * @return OK
     */
    @PutMapping("/step-id/{id}")
    private ResponseEntity<Response<String>> updateActionToRole(
            @PathVariable("id") Integer stepId,
            @RequestBody StepRoleActionRequest stepRoleActionRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(stepRoleActionRequest.toString(), Constant.PUT_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepRoleActionService.updateActionToRole(stepId, stepRoleActionRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}