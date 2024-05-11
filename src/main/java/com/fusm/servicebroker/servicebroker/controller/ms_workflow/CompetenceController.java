package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.CompetenceRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICompetenceService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con las competencias del banco de problemas
 * ITSense Inc - Andrea Gómez
 */


@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/competences")
public class CompetenceController {

    @Autowired
    private ICompetenceService competenceService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un competencia
     * @param competenceRequest Modelo que tiene la información para crear una nueva competencia
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createCompetence(
            @RequestBody CompetenceRequest competenceRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(competenceRequest.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        competenceService.createCompetence(competenceRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene una lista de competencias
     * @param isNif TRUE o FALSE si son las competencias de un NIF o no
     * @return lista de competencias
     */
    @GetMapping("/is-nif/{is-nif}")
    private ResponseEntity<Response<Object>> getCompetences(
            @PathVariable("is-nif") Boolean isNif,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, competenceService.getCompetences(isNif))
        );
    }

    /**
     * Actualizar competencia
     * @param competenceRequest Modelo que contiene la información para actualizar una competencia
     * @param competenceId ID de la competencia
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateCompetence(
            @RequestBody CompetenceRequest competenceRequest,
            @PathVariable("id") Integer competenceId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(competenceRequest.toString(), Constant.PUT_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        competenceService.updateCompetence(competenceRequest, competenceId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
