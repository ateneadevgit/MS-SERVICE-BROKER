package com.fusm.servicebroker.servicebroker.controller.ms_workflow;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.service.ms_workflow.ISyllabusService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import com.itextpdf.text.DocumentException;
import com.fusm.servicebroker.servicebroker.model.ms_workflow.SyllabusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.validation.Valid;

/**
 * Clase que expone los servicios relacionados con el silabos
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.WORKFLOW_ROUTE + "/syllabus")
public class SyllabusController {

    @Autowired
    private ISyllabusService syllabusService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene el PDF del silabos según una asignatura
     * @param curriculumId ID de la asignatura
     * @return URL del pdf
     * @throws DocumentException
     * @throws IOException
     */
    @GetMapping("/curriculum-id/{id}")
    public ResponseEntity<Response<String>> getSyllabus(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws DocumentException, IOException {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, syllabusService.syllabusPdf(curriculumId))
        );
    }

    /**
     * Obtiene la información del silabos
     * @param curriculumId ID de la asignatura
     * @return silabos
     */
    @GetMapping("/data/curriculum-id/{id}")
    public ResponseEntity<Response<Object>> getSyllabusData(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, syllabusService.getSyllabusByCurriculum(curriculumId))
        );
    }

    /**
     * Obtiene la información precargada del silabos
     * @param curriculumId ID de la asignatura
     * @return data a precargar
     */
    @GetMapping("/preload-information/{curriculumId}")
    public ResponseEntity<Response<Object>> getPreloadInformation(
            @PathVariable("curriculumId") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, syllabusService.getPreloadInformation(curriculumId))
        );
    }

    /**
     * Crea el silabos
     * @param syllabusModel Modelo que contiene la información para crear un silabos
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createSyllabus(
            @Valid @RequestBody SyllabusModel syllabusModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(syllabusModel.toString(), Constant.POST_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        syllabusService.createSyllabusAndComplementaryInformation(syllabusModel);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene si el silabos ya existe o no
     * @param curriculumId ID de la asignatura
     * @return TRUE o FALSE
     */
    @GetMapping("/exist/curriculum-id/{id}")
    public ResponseEntity<Response<Boolean>> getSyllabusExist(
            @PathVariable("id") Integer curriculumId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, syllabusService.getSyllabusExist(curriculumId))
        );
    }

    /**
     * Actualiza el silabos
     * @param syllabusId ID del silabos
     * @param syllabusModel Modelo que contiene la información a actualizar del silabos
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<String>>  updateSyllabus(
            @PathVariable("id") Integer syllabusId,
            @Valid @RequestBody SyllabusModel syllabusModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(syllabusModel.toString(), Constant.PUT_METHOD, false, Constant.WORKFLOW_MODULE, authorizationHeader);
        syllabusService.updateSyllabus(syllabusId, syllabusModel);

        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }
}
