package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SecondLanguageRequest;
import com.fusm.servicebroker.servicebroker.service.ms_program.ISecondLanguageService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios necesarios para el centro de aprendizaje de una segunda lengua
 * ITSense Inc - Andrea Gómez
 */


@RestController
@RequestMapping(AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/second-language")
public class SecondLanguageController {

    @Autowired
    private ISecondLanguageService secondLanguageService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un nuevo nivel de segunda lengua
     * @param secondLanguageRequest Modelo que contiene la información necesaria para crear un nuevo nivel
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createSecondLanguage(
            @RequestBody SecondLanguageRequest secondLanguageRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(secondLanguageRequest.toString(), Constant.POST_METHOD, false, Constant.SECOND_LANGUAGE_MODULE, authorizationHeader);
        secondLanguageService.createSecondLanguage(secondLanguageRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un nivel de segunda lengua
     * @param secondLanguageRequest Modelo que contiene la información necesaria para actualizar un nivel
     * @param secondLanguageId ID del nivel
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateSecondLanguage(
            @RequestBody SecondLanguageRequest secondLanguageRequest,
            @PathVariable("id") Integer secondLanguageId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(secondLanguageRequest.toString(), Constant.PUT_METHOD, false, Constant.SECOND_LANGUAGE_MODULE, authorizationHeader);
        secondLanguageService.updateSecondLanguage(secondLanguageRequest, secondLanguageId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Dehabilita un nivel
     * @param secondLanguageId ID del nivel
     * @return OK
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Response<String>> disableSecondLanguage(
            @PathVariable("id") Integer secondLanguageId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.SECOND_LANGUAGE_MODULE, authorizationHeader);
        secondLanguageService.disableSecondLanguage(secondLanguageId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene una lista de los niveles
     * @return lista de niveles
     */
    @GetMapping
    private ResponseEntity<Response<Object>> getSecondLanguages(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SECOND_LANGUAGE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, secondLanguageService.getSecondLanguages())
        );
    }

    /**
     * Obtuiene una lista de los niveles agrupados por los grupos
     * @return lista de niveles
     */
    @GetMapping("/group")
    private ResponseEntity<Response<Object>> getSecondLanguageByGroup(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SECOND_LANGUAGE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, secondLanguageService.getSecondLanguagesByGroup())
        );
    }

    /**
     * Obtiene el nivel por su ID
     * @param secondLanguageId ID del nivel
     * @return nivel
     */
    @GetMapping("/{id}")
    private ResponseEntity<Response<Object>> getSecondLanguageById(
            @PathVariable("id") Integer secondLanguageId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SECOND_LANGUAGE_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, secondLanguageService.getSecondLanguageById(secondLanguageId))
        );
    }

}

