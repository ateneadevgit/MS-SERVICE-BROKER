package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.*;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ICurriculumService;
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
 * Clase que expone los servicios relacionados con los niveles de la estructura curricular de un programa académico
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/curriculum")
public class CurriculumController {

    @Autowired
    private ICurriculumService curriculumService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Crea un nuevo nivel de la estructura curricular
     * @param curriculumRequest Modelo que contiene la información para crear un nivel
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createCurriculum(
            @Valid @RequestBody CurriculumListRequest curriculumRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(curriculumRequest.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        curriculumService.createCurriculum(curriculumRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la lista de los niveles en forma de árbol de la estructura curricular
     * @param objectId ID del programa
     * @return lista de niveles
     */
    @GetMapping("/object-id/{id}")
    public ResponseEntity<Response<Object>> getCurriculum(
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculum(objectId))
        );
    }

    /**
     * Actualiza el nivel de la estructura curricular
     * @param updateCurriculumRequest Modelo que contiene la información a actualizar del nivel
     * @param curriculumId ID del nivel
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateCurriculum(
            @Valid @RequestBody UpdateCurriculumRequest updateCurriculumRequest,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(updateCurriculumRequest.toString(), Constant.PUT_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        curriculumService.updateCurriculum(updateCurriculumRequest, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Deshabilitar un nivel
     * @param curriculumId ID del nivel
     * @return OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> disableCurriculum(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        curriculumService.disableCurriculum(curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene el contenido de un nivel
     * @param objectId ID del programa
     * @param curriculumType Tipo del nivel
     * @return lista de niveles
     */
    @GetMapping("/object-id/{id}/by-type/{type}")
    public ResponseEntity<Response<Object>> getCurriculumByType(
            @PathVariable("id") Integer objectId,
            @PathVariable("type") Integer curriculumType,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculumByType(objectId, curriculumType))
        );
    }

    /**
     * Obtiene los niveles según su padre
     * @param objectId ID del nivel
     * @param curriculumFatherId ID del nivel padre
     * @return lista de niveles
     */
    @GetMapping("/object-id/{id}/by-father/{father}")
    public ResponseEntity<Response<Object>> getCurriculumByFather(
            @PathVariable("id") Integer objectId,
            @PathVariable("father") Integer curriculumFatherId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculumByFather(objectId, curriculumFatherId))
        );
    }

    /**
     * Calcula el porcentaje de participación de la estructura curricular
     * @param objectId ID del programa
     * @return porcentaje de participación
     */
    @GetMapping("/object-id/{id}/calculate/percentage")
    public ResponseEntity<Response<String>> calculateParticipationPercentage(
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        curriculumService.calculateParticipationPercentage(objectId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene la lista de asignaturas de un programa
     * @param obejctId ID del programa
     * @return lista de asignaturas
     */
    @GetMapping("/object-id/{id}/subjects")
    public ResponseEntity<Response<Object>> getSubjects(
            @PathVariable("id") Integer obejctId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getSubjects(obejctId))
        );
    }

    /**
     * Obtiene el número total de créditos de la estructura curricular
     * @param objectId ID del programa
     * @return número total de créditos
     */
    @GetMapping("/credits/object-id/{id}")
    public ResponseEntity<Response<Integer>> getObjectNumberCredits(
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculumCredits(objectId))
        );
    }

    /**
     * Obtiene las asignaturas agrupadas por semestre
     * @param objectId ID del programa
     * @return lista de asignaturas
     */
    @GetMapping("/semester/object-id/{id}")
    public ResponseEntity<Response<Object>> getCurriculumSemesterByProgram(
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculumSemesterByProgram(objectId))
        );
    }

    /**
     * Obtiene los detalles de un nivel
     * @param curriculumId ID del nivel
     * @return nivel
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Response<Object>> getCurriculumDetail(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculumDetailById(curriculumId))
        );
    }

    /**
     * General el PDF de la estructura curridular
     * @param programId ID del programa
     * @return URL del pdf
     */
    @GetMapping("/pdf/object-id/{id}")
    public ResponseEntity<Response<String>> getCurriculumPdf(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.curriculumPdf(programId))
        );
    }

    /**
     * Obtiene una lista de los NIFS
     * @return lista de NIFS
     */
    @GetMapping("/nif")
    public ResponseEntity<Response<Object>> getNifsCurriculum(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getNifsCurriculum())
        );
    }

    /**
     * Crea un NIF
     * @param subcoreNifsRequest modelo que contiene la información para crear un nuevo NIF
     * @return OK
     */
    @PostMapping("/nif")
    public ResponseEntity<Response<String>> createNifsCurriculum(
            @RequestBody SubcoreNifsRequest subcoreNifsRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(subcoreNifsRequest.toString(), Constant.POST_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        curriculumService.createNifsCurriculum(subcoreNifsRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los semestres de un programa académico
     * @param objectId
     * @return
     */
    @GetMapping("/semester-number/object-id/{id}")
    public ResponseEntity<Response<Object>> getSemesterByProgram(
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getSemestersByProgram(objectId))
        );
    }

    /**
     * Obtiene los programas de asignatura
     * @param SearchSubject Modelo que contiene la información para filtrar la consulta
     * @param params parámetros como page y size para definir la paginación de la respuesta
     * @return lista de programas de asignatura
     */
    @PostMapping("/program-subject")
    public ResponseEntity<Response<Object>> getProgramSubject(
            @RequestBody SearchSubject SearchSubject,
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int size = params.get("size") != null ? Integer.parseInt(params.get("size").toString()) : 10;
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getProgramSubject(
                        SearchSubject,
                        PageModel.builder()
                                .page(page)
                                .size(size)
                                .build()))
        );
    }

    /**
     * Obtiene una lista de docentes segun una asignatura
     * @param curriculumId ID de la asignatura
     * @return lista de docentes
     */
    @GetMapping("/{id}/teacher")
    public ResponseEntity<Response<Object>> getTeachersBySubject(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.SIGNATURE_PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getTeachersBySubject(curriculumId))
        );
    }

    /**
     * Actualizar la información complementaria de los NIFS
     * @param complementaryNifs modelo que contiene la información a actualizar del NIF
     * @param curriculumId ID del NIF
     * @return OK
     */
    @PutMapping("/{id}/complementary-nif")
    public ResponseEntity<Response<String>> updateComplementaryNifs(
            @RequestBody UpdateComplementaryNifs complementaryNifs,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(complementaryNifs.toString(), Constant.PUT_METHOD, false, Constant.NIFS_MODULE, authorizationHeader);
        curriculumService.updateComplementaryNifs(complementaryNifs, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene los niveles de un programa
     * @param objectId ID del programa
     * @return lista de niveles
     */
    @GetMapping("/levels/object-id/{id}")
    private ResponseEntity<Response<Object>> getLevelsByProgram(
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getLevelsByProgram(objectId))
        );
    }

    /**
     * Crea una evaluación complementaria
     * @param complementaryEvaluationRequest Modelo que contiene la informaicón necesaria para crear una evaluación
     * @param curriculumId ID del nivel
     * @return OK
     */
    @PostMapping("/{id}/complementary-evaluation")
    private ResponseEntity<Response<String>> createComplementaryEvaluation(
            @RequestBody ComplementaryEvaluationRequest complementaryEvaluationRequest,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(complementaryEvaluationRequest.toString(), Constant.POST_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        curriculumService.createComplementaryEvaluation(complementaryEvaluationRequest, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza la información de la evaluación complementaria
     * @param complementaryEvaluationRequest Modelo que contiene la información para actualizar una evaluación
     * @param curriculumId ID del nivel
     * @return OK
     */
    @PutMapping("/{id}/complementary-evaluation")
    private ResponseEntity<Response<String>> updateComplementaryEvaluation(
            @RequestBody ComplementaryEvaluationRequest complementaryEvaluationRequest,
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(complementaryEvaluationRequest.toString(), Constant.PUT_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        curriculumService.updateComplementaryEvaluation(complementaryEvaluationRequest, curriculumId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtener las evaluaciones según el nivel
     * @param curriculumType ID del tipo de nivel
     * @param objectId ID del programa
     * @return lista de evaluaciones
     */
    @GetMapping("/object-id/{id}/by-type/{type}/complementary-evaluation")
    private ResponseEntity<Response<Object>> getCurriculumEvaluationByType(
            @PathVariable("type") Integer curriculumType,
            @PathVariable("id") Integer objectId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.LEARNING_ASSESSMENT_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurriculumEvaluationByType(objectId, curriculumType))
        );
    }

    /**
     * Obtener el progreso del programa
     * @param programId ID del programa
     * @return progreso del programa
     */
    @GetMapping("/object-id/{id}/progress")
    private ResponseEntity<Response<Object>> getProgramProgress(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DASHBOARD_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getProgramProgress(programId))
        );
    }

    /**
     * Obtiene el histórico de asignaturas cursadas por un usuario
     * @param programId ID del programa
     * @param userId ID del usuario
     * @return lista de asignaturas
     */
    @GetMapping("/object-id/{id}/historic")
    private ResponseEntity<Response<Object>> getHistorySubject(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DASHBOARD_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getHistorySubject(programId))
        );
    }

    /**
     * Obtiene las asignaturas cursadas actualmente por usuario
     * @param programId ID del programa
     * @return lista de asignaturas
     */
    @GetMapping("/object-id/{id}/current")
    private ResponseEntity<Response<Object>> getCurrentSubject(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DASHBOARD_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, curriculumService.getCurrentSubject(programId))
        );
    }

}
