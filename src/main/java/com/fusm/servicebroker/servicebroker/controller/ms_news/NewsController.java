package com.fusm.servicebroker.servicebroker.controller.ms_news;

import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_news.NewsRequest;
import com.fusm.servicebroker.servicebroker.model.ms_news.NewsSearch;
import com.fusm.servicebroker.servicebroker.service.ms_news.INewsService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con las noticias
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.NEWS_ROUTE)
public class NewsController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene las noticias
     * @param newsSearch Modelo que contiene los parámetros de búsqueda para filtrar
     * @return lista de noticias
     */
    @PostMapping("/get")
    private ResponseEntity<Response<Object>> getNews(
            @RequestBody NewsSearch newsSearch,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.NEW_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, newsService.getNews(newsSearch))
        );
    }

    /**
     * Crea una noticia
     * @param newsRequest Modelo que contiene la información encesaria para crear noticias
     * @return OK
     */
    @PostMapping
    private ResponseEntity<Response<String>> createNews(
            @RequestBody NewsRequest newsRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(newsRequest.toString(), Constant.POST_METHOD, true, Constant.NEW_MODULE, authorizationHeader);
        newsService.createNew(newsRequest);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Actualzia las noticias
     * @param newsRequest Modelo que contiene la información necesaria para actualizar una noticia
     * @param newId ID de la noticia
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<Response<String>> updateNews(
            @RequestBody NewsRequest newsRequest,
            @PathVariable("id") Integer newId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(newsRequest.toString(), Constant.PUT_METHOD, true, Constant.NEW_MODULE, authorizationHeader);
        newsService.updateNews(newsRequest, newId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

    /**
     * Elimina una noticia
     * @param newId ID de la noticia
     * @return OK
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Response<String>> deleteNew(
            @PathVariable("id") Integer newId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.DELETE_METHOD, false, Constant.NEW_MODULE, authorizationHeader);
        newsService.disableNews(newId);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase())
        );
    }

}
