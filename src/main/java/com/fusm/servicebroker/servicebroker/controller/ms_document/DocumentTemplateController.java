package com.fusm.servicebroker.servicebroker.controller.ms_document;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.service.ms_document.IDocumentTemplateService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone los servicios necesarios para los PDF de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.DOCUMENT_ROUTE + "/pdf")
public class DocumentTemplateController {

    @Autowired
    private IDocumentTemplateService documentTemplateService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene la plantilla de un PDF
     * @param templateId ID de la plantilla
     * @return plantilla
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> getDocumentTemplate(
            @PathVariable("id") Integer templateId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(documentTemplateService.getTemplate(templateId));
    }

}
