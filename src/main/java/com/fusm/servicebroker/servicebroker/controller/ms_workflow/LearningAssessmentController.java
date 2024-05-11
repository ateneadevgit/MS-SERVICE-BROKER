package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.LearningAssessmentRequest;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ILearningAssessmentService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios relacionados con las evaluaciones de aprendizaje
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/learning")
public class LearningAssessmentController {

    @Autowired
    private ILearningAssessmentService learningAssessmentService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obntiene las evaluaciones de aprendizaje según un nivel de la estructura curricular
     *
     * @param curriculumId ID del nivel
     * @return lista de evaluaciones
     */
    @GetMapping("/curriculum-id/{id}")
    private ResponseEntity<Response<Object>> getLearningAssessmentByCurriculumId(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, learningAssessmentService.getLearningAssessmentByCurriculumId(curriculumId))
        );
    }

    /**
     * Crea una evaluación de aprendizaje
     *
     * @param learningAssessmentRequest Modelo que contiene la información para crear una evaluación de aprendizaje
     * @param curriculumId              ID del nivel
     * @return OK
     */
    @PostMapping("/curriculum-id/{id}")
    private ResponseEntity<Response<String>> createLearningAssessment(
            @RequestBody LearningAssessmentRequest<FileModel> learningAssessmentRequest,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(learningAssessmentRequest.toString(), Constant.POST_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        learningAssessmentService.createLearningAssessment(learningAssessmentRequest, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualizar una evaluación de aprendizaje
     *
     * @param learningAssessmentRequest Modelo que contiene la información a actualizar de la evaluación de aprendizaje
     * @param learningAssessmentId      ID de la evaluación
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateLearningAssessment(
            @RequestBody LearningAssessmentRequest<FileModel> learningAssessmentRequest,
            @PathVariable("id") Integer learningAssessmentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(learningAssessmentRequest.toString(), Constant.PUT_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        learningAssessmentService.updateLearningAssessment(learningAssessmentRequest, learningAssessmentId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Deshabilita una evaluación de aprendizaje
     * @param learningAssessmentId ID de la evaluación
     * @return OK
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Response<String>> disableLearningAssessment(
            @PathVariable("id") Integer learningAssessmentId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        learningAssessmentService.disableLearningAssessment(learningAssessmentId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}

