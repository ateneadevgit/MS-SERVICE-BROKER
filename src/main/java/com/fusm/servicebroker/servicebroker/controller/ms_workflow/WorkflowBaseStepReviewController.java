package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewListModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.ReviewRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.IWorkflowBaseStepReviewService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Clase que expone los servicios para crear comentarios sobre los paso del flujo de creación de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/step/review")
public class WorkflowBaseStepReviewController {

    @Autowired
    private IWorkflowBaseStepReviewService workflowBaseStepReviewService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un comentario
     * @param reviewRequest Modelo que contiene la información para crear el comentario
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createReview(
            @Valid @RequestBody ReviewRequest reviewRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(reviewRequest.toString(), Constant.POST_METHOD, true, Constant.WORKFLOW_MODULE, authorizationHeader);
        workflowBaseStepReviewService.createReview(reviewRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los comentarios realizados sobre un paso
     * @return lista de comentarios
     */
    @GetMapping
    public ResponseEntity<Response<Object>> getReviews(
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, workflowBaseStepReviewService.getReviewByStep(params))
        );
    }

}
