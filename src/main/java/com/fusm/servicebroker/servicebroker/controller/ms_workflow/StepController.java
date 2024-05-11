package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.StepRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IStepService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con los pasos de la creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/step")
public class StepController {

    @Autowired
    private IStepService stepService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un paso
     * @param stepRequest Modelo que contiene la información para crear un nuevo paso
     * @param workflowId ID del flujo de trabajo
     * @return OK
     */
    @PostMapping("/workflow-id/{id}")
    private ResponseEntity<Response<String>> createStep(
            @RequestBody StepRequest stepRequest,
            @PathVariable("id") Integer workflowId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(stepRequest.toString(), Constant.POST_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepService.createStep(stepRequest, workflowId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Habilitar o Deshabilitar un paso
     * @param stepId ID del paso
     * @param workflowId ID del flujo de trabajo
     * @param enabled TRUE o FALSE en caso que se quiera habilitar o deshabilitar
     * @return OK
     */
    @DeleteMapping("/{id}/workflow-id/{workflow-id}/enable/{enabled}")
    private ResponseEntity<Response<String>> enableDisableStep(
            @PathVariable("id") Integer stepId,
            @PathVariable("workflow-id") Integer workflowId,
            @PathVariable("enabled") Boolean enabled,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepService.enableDisableStep(stepId, workflowId, enabled);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtener paso por su ID
     * @param stepId ID del paso
     * @param workflowId ID del flujo de trabajo
     * @return paso
     */
    @GetMapping("/{id}/workflow-id/{workflow-id}")
    private ResponseEntity<Response<Object>> getStepById(
            @PathVariable("id") Integer stepId,
            @PathVariable("workflow-id") Integer workflowId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, stepService.getStepById(stepId, workflowId))
        );
    }

    /**
     * Actualiza el paso
     * @param stepRequest Modelo que contiene la información a actualizar de un paso
     * @param stepId ID del paso
     * @param workflowId ID del flujo de trabajo
     * @return OK
     */
    @PutMapping("/{id}/workflow-id/{workflow-id}")
    private ResponseEntity<Response<String>> updateStep(
            @RequestBody StepRequest stepRequest,
            @PathVariable("id") Integer stepId,
            @PathVariable("workflow-id") Integer workflowId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(stepRequest.toString(), Constant.PUT_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        stepService.updateStep(stepRequest, workflowId, stepId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
