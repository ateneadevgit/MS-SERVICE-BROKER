package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.GuidelineRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.SearchModel;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProgramAttachmentService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con los documentos del programa presentes en los lineamientos
 * institucionales de gestión curricular y comités
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE + "/attachment")
public class ProgramAttachmentController {

    @Autowired
    private IProgramAttachmentService programAttachmentService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obntiene todos los documentos
     * @return lista de documentos
     */
    @GetMapping("/guideline")
    public ResponseEntity<Response<Object>> getAllGuideline(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programAttachmentService.getGuidelineAttachment())
        );
    }

    /**
     * Obtiene lista de todos los documentos segun el tipo
     * @param type tipo de documento si es guideline o minute
     * @return lsita de documentos
     */
    @GetMapping("/guideline/by-type/{type}")
    public ResponseEntity<Response<Object>> getGuidelineAttachmentByType(
            @PathVariable("type") Integer type,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DOCUMENT_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programAttachmentService.getGuidelineAttachmentByType(type))
        );
    }

    /**
     * Obtiene una lsita de todas las actas
     * @param searchModel Modelo que contiene los parámetros de búsqueda prara filtrar
     * @return lista de actas
     */
    @PostMapping("/minute")
    public ResponseEntity<Object> getAllMinute(
            @RequestBody SearchModel searchModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(programAttachmentService.getMinutes(searchModel));
    }

    /**
     * Crea un lineamiento institucional
     * @param guidelineRequest Modelo que contiene la información necesaria para crear un nuevo lineamiento institucional
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createGuideline(
            @RequestBody GuidelineRequest guidelineRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(guidelineRequest.toString(), Constant.POST_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        programAttachmentService.createGuidelineAttachment(guidelineRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un lineamiento institucional
     * @param guidelineRequest Modelo que contiene la información necesaria para actualizar un lineamiento isntitucional
     * @param guidelineId ID del lineamiento institucional
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateGuideline(
            @RequestBody GuidelineRequest guidelineRequest,
            @PathVariable("id") Integer guidelineId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(guidelineRequest.toString(), Constant.PUT_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        programAttachmentService.updateGuidelineAttachment(guidelineRequest, guidelineId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Deshabilita un lineamiento institucional
     * @param guidelineId ID del lineamiento institucional
     * @return OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteGuideline(
            @PathVariable("id") Integer guidelineId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        programAttachmentService.disableGuidelineAttachment(guidelineId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Elimina de forma permanente un lineamiento institucional
     * @param guidelineId ID del linemiento institucional
     * @return OK
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<String>> deletePermanentlyGuideline(
            @PathVariable("id") Integer guidelineId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.DOCUMENT_MODULE, authorizationHeader);
        programAttachmentService.deleteGuidelineAttachment(guidelineId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Habilita o Deshabilita un lineamiento institucional
     * @param guidelineId ID del lineamiento institucional
     * @param enabled Parámetro que indica si se activa o desactiva el lineamiento institucional
     * @return OK
     */
    @DeleteMapping("/{id}/enable-disable/{enabled}")
    public ResponseEntity<Response<String>> enableDisableGuideline(
            @PathVariable("id") Integer guidelineId,
            @PathVariable("enabled") Boolean enabled,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.DOCUMENT_MODULE, authorizationHeader);
        programAttachmentService.enableDisableGuideline(guidelineId, enabled);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Obtiene el linemiento institucional según su ID
     * @param guidelineId ID del lineamiento institucional
     * @return URL del lineamiento institucional
     */
    @GetMapping("/guideline/{id}")
    public ResponseEntity<Response<String>> getGuidelineAttachmentById(
            @PathVariable("id") Integer guidelineId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programAttachmentService.getGuidelineAttachmentById(guidelineId))
        );
    }

}
