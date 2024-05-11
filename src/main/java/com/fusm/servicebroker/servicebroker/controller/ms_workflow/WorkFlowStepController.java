package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowStepService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con los pasos de los flujos de trabajo de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/workflow-step")
public class WorkFlowStepController {

    @Autowired
    private IWorkflowStepService workflowStepService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los pasos de un flujo de trabajo
     * @param workflowId ID del flujo de trabajo
     * @return lista de flujos de trabajo
     */
    @GetMapping("/{id}")
    private ResponseEntity<Response<Object>> getStepsByWorkflow(
            @PathVariable("id") Integer workflowId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.FLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, workflowStepService.getStepsByWorkflow(workflowId))
        );
    }

}