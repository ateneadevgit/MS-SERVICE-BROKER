package com.fusm.servicebroker.servicebroker.controller.ms_document;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_document.DocumentRequest;
import com.fusm.servicebroker.servicebroker.service.ms_document.IDocumentManagerService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone todos los servicios relacionados con los archivos dentro de la aplicación
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.DOCUMENT_ROUTE)
public class DocumentManagerController {

    @Autowired
    private IDocumentManagerService documentManagerService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Guarda un archivo en el servidor
     * @param documentRequest Modelo que contiene la información del archivo a almacenar en el servidor
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createFile(
            @RequestBody DocumentRequest documentRequest,
            @RequestHeader("Authorization") String authorizationHeader
            ) {
        middlewareService.allMiddlewares(documentRequest.toString(), Constant.POST_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, documentManagerService.saveDocument(documentRequest))
        );
    }

}
