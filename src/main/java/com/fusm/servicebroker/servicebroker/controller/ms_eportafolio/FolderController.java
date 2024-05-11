package com.fusm.servicebroker.servicebroker.controller.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FolderRequest;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.IFolderService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase que expone todos los servicios relacioneados con las carpetas del e-portafolio
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(AppRoutes.PUBLIC_ROUTE + AppRoutes.EPORTAFOLIO_ROUTE + "/folder")
public class FolderController {

    @Autowired
    private IFolderService folderService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene las carpetas segun un usuario
     * @return lista de carpetas
     */
    @GetMapping
    public ResponseEntity<Response<Object>> getFoldersByUser(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, folderService.getFoldersByUser())
        );
    }

    /**
     * Crea una carpeta
     * @param folderRequest Modelo que contiene la información necesaria para crear una carpeta
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createFolder(
            @RequestBody FolderRequest folderRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(folderRequest.toString(), Constant.POST_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        folderService.createFolder(folderRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza una carpeta
     * @param folderRequest Modelo que contiene la información necesaria para actualizar una carpeta
     * @param folderId ID de la carpeta
     * @return OK
     */
    @PutMapping("/{folderId}")
    public ResponseEntity<Response<String>> updateFolder(
            @RequestBody FolderRequest folderRequest,
            @PathVariable("folderId") Integer folderId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(folderRequest.toString(), Constant.PUT_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        folderService.updateFolder(folderRequest, folderId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Elimina una carpeta y todos los archivos que la contiene
     * @param folderId ID de la carpeta
     * @return OK
     */
    @DeleteMapping("/{folderId}")
    public ResponseEntity<Response<String>> deleteFolder(
            @PathVariable("folderId") Integer folderId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        folderService.deleteFolder(folderId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}

