package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.ReviewGeneralRequest;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.GetReviewModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewRequest;
import com.fusm.servicebroker.servicebroker.service.ms_program.IReviewService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con los comentarios de la edición de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un nuevo comentario dentro del módulo
     * @param reviewRequest Modelo que contiene la información necesaria para crear un comentario
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createReview(
            @RequestBody ReviewGeneralRequest reviewRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(reviewRequest.toString(), Constant.POST_METHOD, true, Constant.PROGRAM_MODULE, authorizationHeader);
        reviewService.createReview(reviewRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los comentarios realizados sobre un módulo
     * @param getReviewModel Modelo que contiene la información para filtrar la consulta
     * @return lsita de comentarios
     */
    @PostMapping("/get")
    public ResponseEntity<Response<Object>> getReviews(
            @RequestBody GetReviewModel getReviewModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, true, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, reviewService.getReviews(getReviewModel))
        );
    }

}