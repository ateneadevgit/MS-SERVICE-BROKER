package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.CurriculumChatRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.GetReviewModel;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumChatService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios del chat del módulo de programas de asignatura
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/curriculum/chat")
public class CurriculumChatController {

    @Autowired
    private ICurriculumChatService curriculumChatService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un comentario
     * @param curriculumChatRequest Modelo que contiene la información para crear un comentario
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createReview(
            @RequestBody CurriculumChatRequest curriculumChatRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(curriculumChatRequest.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        curriculumChatService.createReview(curriculumChatRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la lista de comentarios
     * @param getReviewModel Modelo que contiene la información para realizar la consulta
     * @return lista de comentarios
     */
    @PostMapping("/get")
    private ResponseEntity<Response<Object>> getReview(
            @RequestBody GetReviewModel getReviewModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumChatService.getReviews(getReviewModel))
        );
    }

    /**
     * Lee el comentario
     * @param curriculumChats Modelo que contiene la información necesaria para realizar
     * @return OK
     */
    @PostMapping("/read")
    private ResponseEntity<Response<Object>> getProgramByFaculties(
            @RequestBody List<Integer> curriculumChats,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(curriculumChats.toString(), Constant.POST_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        curriculumChatService.readCurriculumChat(curriculumChats);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}

