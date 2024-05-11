package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.EvaluateObjectRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ObjectTempRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ObjectToReview;
import com.fusm.servicebroker.servicebroker.service.ms_program.IObjectTempService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los sevicios relacionados con los objetos temporales aplicados en el módulo de ciclo curricular del programa académico o edición de un programa
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value =  AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/object")
public class ObjectTempController {

    @Autowired
    private IObjectTempService objectTempService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un objeto temporal
     * @param programId ID del programa
     * @param objectTempRequest Modelo que contiene la información necesaria para crear un objeto temporal
     * @return OK
     * @throws JsonProcessingException
     */
    @PostMapping("/program/{id}")
    public ResponseEntity<Response<String>> createdObjectTemp(
            @PathVariable("id") Integer programId,
            @RequestBody ObjectTempRequest objectTempRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws JsonProcessingException {
        middlewareService.allMiddlewares(objectTempRequest.toString(), Constant.POST_METHOD, true, Constant.PROGRAM_MODULE, authorizationHeader);
        objectTempService.createdObjectTemp(objectTempRequest, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Evalúa un objeto temporal
     * @param programId ID del programa
     * @param evaluateObjectRequest Modelo que contiene la información del objeto a evaluar
     * @return OK
     * @throws JsonProcessingException
     */
    @PostMapping("/evaluate/program/{id}")
    public ResponseEntity<Response<String>> evaluateObject(
            @PathVariable("id") Integer programId,
            @RequestBody EvaluateObjectRequest evaluateObjectRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws JsonProcessingException {
        middlewareService.allMiddlewares(evaluateObjectRequest.toString(), Constant.POST_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        objectTempService.evaluateObject(evaluateObjectRequest, programId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la información de un objeto temporal
     * @param moduleId ID del módulo
     * @param programId ID del programa
     * @return objeto temporal
     */
    @GetMapping("/module-id/{moduleId}/program-id/{programId}")
    public ResponseEntity<Response<Object>> getObjectTemp(
            @PathVariable("moduleId") Integer moduleId,
            @PathVariable("programId") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, objectTempService.getObjectTemp(programId, moduleId))
        );
    }

    /**
     * Actualiza un objeto temporal
     * @param objectTempRequest Modelo que contiene la información para actualizar un objeto temporal
     * @return OK
     * @throws JsonProcessingException
     */
    @PutMapping("/program-id/{id}")
    public ResponseEntity<Response<String>> updateObjectTemp(
            @PathVariable("id") Integer obejctId,
            @RequestBody ObjectTempRequest objectTempRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(objectTempRequest.toString(), Constant.PUT_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        objectTempService.updateObjectTemp(objectTempRequest, obejctId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la cantidad de créditos del objeto temporal
     * @param moduleId ID del módulo
     * @param programId ID del programa
     * @return número de créditos
     * @throws JsonProcessingException
     */
    @GetMapping("/module-id/{moduleId}/program-id/{programId}/credits")
    public ResponseEntity<Response<Integer>> getObjectNumberCredits(
            @PathVariable("moduleId") Integer moduleId,
            @PathVariable("programId") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws JsonProcessingException {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, objectTempService.getObjectNumberCredits(moduleId, programId))
        );
    }

    /**
     * Obtiene los núcleos de un objeto temporal
     * @param moduleId ID del módulo
     * @param programId ID del programa
     * @return lista de núcleos y subnúcleos
     * @throws JsonProcessingException
     */
    @GetMapping("/module-id/{moduleId}/program-id/{programId}/core")
    public ResponseEntity<Response<Object>> getCores(
            @PathVariable("moduleId") Integer moduleId,
            @PathVariable("programId") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws JsonProcessingException {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, objectTempService.getCores(moduleId, programId))
        );
    }

    /**
     * Obtiene las asignaturas según un núcleo dentro de un objeto temporal
     * @param moduleId ID del módulo
     * @param programId ID del programa
     * @param coreId ID del núcleo
     * @return lista de núcleos y subnúcleos
     * @throws JsonProcessingException
     */
    @GetMapping("/module-id/{moduleId}/program-id/{programId}/by-core/{coreId}")
    public ResponseEntity<Response<Object>> getSubjectsByCore(
            @PathVariable("moduleId") Integer moduleId,
            @PathVariable("programId") Integer programId,
            @PathVariable("coreId") Integer coreId,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws JsonProcessingException {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, objectTempService.getSubjectsByCore(coreId, moduleId, programId))
        );
    }

    /**
     * Envía el módulo a revisión
     * @param objectToReview Modelo que contiene la información del módulo que se enviará a revisión
     * @return OK
     * @throws JsonProcessingException
     */
    @PostMapping("/send-review")
    public ResponseEntity<Response<String>> sendModuleToReview(
            @RequestBody ObjectToReview objectToReview,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(objectToReview.toString(), Constant.POST_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        objectTempService.sendObjectToReview(objectToReview);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}

