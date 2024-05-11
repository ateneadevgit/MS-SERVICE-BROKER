package com.fusm.servicebroker.servicebroker.controller.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileRequest;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import com.fusm.servicebroker.servicebroker.model.ms_program.FileModel;
import com.fusm.servicebroker.servicebroker.service.ms_eportafolio.IFileService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Clase que expone todos los servicios relacionados con los archivos del e-portafolio
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(AppRoutes.PUBLIC_ROUTE + AppRoutes.EPORTAFOLIO_ROUTE + "/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los archivos según una carpeta
     *
     * @param fileSearch Modelo que contiene los parámetros de búsqueda para realizar el filtrado
     * @param folderId   ID de la carpeta
     * @param params     Parámetros como page y size para la paginación de la respeusta
     * @return lista de archivos
     */
    @PostMapping("/folder-id/{folderId}")
    public ResponseEntity<Response<Object>> getFilesByFolder(
            @RequestBody FileSearch fileSearch,
            @PathVariable("folderId") Integer folderId,
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        int page = params.get("page") != null ? Integer.parseInt(params.get("page").toString()) : 1;
        int size = params.get("size") != null ? Integer.parseInt(params.get("size").toString()) : 10;
        return ResponseEntity.ok(
                new Response<>(
                        HttpStatus.OK,
                        fileService.getFilesByFolder(
                                fileSearch,
                                PageModel.builder()
                                        .page(page)
                                        .size(size)
                                        .build(),
                                folderId)
                )
        );
    }

    /**
     * Obtener archivo según su ID
     *
     * @param fileId ID del archivo
     * @return Archivo
     */
    @GetMapping("/{fileId}")
    public ResponseEntity<Response<Object>> getFileById(
            @PathVariable("fileId") Integer fileId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, fileService.getFileById(fileId))
        );
    }

    /**
     * Obtiene el espacio consumido por un usuario
     * @return Modelo que representa el total de espacio consumido por un usuario
     */
    @GetMapping("/consumed")
    public ResponseEntity<Response<Object>> getConsumedSpace(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, fileService.getConsumedSpace())
        );
    }

    /**
     * Crea un archivo
     * @param fileRequest Modelo que contiene la información necesaria para crear un archivo
     * @return OK
     */
    @PostMapping
    public ResponseEntity<Response<String>> createFile(
            @RequestBody FileRequest<FileModel> fileRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(fileRequest.toString(), Constant.POST_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        fileService.createFile(fileRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualiza un archivo
     * @param fileRequest Modelo que contiene la información necesaria para actualizar un archivo
     * @param fileId ID del archivo
     * @return OK
     */
    @PutMapping("/{fileId}")
    public ResponseEntity<Response<String>> updateFile(
            @RequestBody FileRequest<FileModel> fileRequest,
            @PathVariable("fileId") Integer fileId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(fileRequest.toString(), Constant.PUT_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        fileService.updateFile(fileRequest, fileId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Elimina un archivo
     * @param fileId ID del archivo
     * @return OK
     */
    @DeleteMapping("/{fileId}")
    public ResponseEntity<Response<String>> deleteFile(
            @PathVariable("fileId") Integer fileId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.EPORTAFOLIO_MODULE, authorizationHeader);
        fileService.deleteFile(fileId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
