package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.AssignCoordinator;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumCoordinatorService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios para asignar un coordinador a una asignatura
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/curriculum")
public class CurriculumCoordinatorController {

    @Autowired
    private ICurriculumCoordinatorService curriculumCoordinatorService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Asigna un coordinador a una asignatura
     * @param assignCoordinator Modelo que ocntiene la información del coordinador a asignar
     * @param curriculumId ID de la asignatura
     * @return OK
     */
    @PostMapping("/{id}/coordinator")
    private ResponseEntity<Response<String>> assignCoordinator(
            @RequestBody AssignCoordinator assignCoordinator,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(assignCoordinator.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        curriculumCoordinatorService.assignCoordinator(assignCoordinator, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la información del coordinador asignado a una asignatura
     * @param curriculumId ID de la asignatura
     * @return coordinador
     */
    @GetMapping("/{id}/coordinator")
    private ResponseEntity<Response<Object>> coordinatorAssigned(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumCoordinatorService.coordinatorAssigned(curriculumId))
        );
    }

}
