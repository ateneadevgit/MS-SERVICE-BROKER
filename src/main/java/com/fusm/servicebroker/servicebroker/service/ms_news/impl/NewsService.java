package com.fusm.servicebroker.servicebroker.service.ms_news.impl;

import com.fusm.servicebroker.servicebroker.model.ms_news.NewsRequest;
import com.fusm.servicebroker.servicebroker.model.ms_news.NewsSearch;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_news.INewsService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements INewsService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-news.complete-path}")
    private String NEWS_ROUTE;

    @Value("${ms-news.path}")
    private String NEWS_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public void createNew(NewsRequest newsRequest) {
        newsRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(NEWS_ROUTE)
                .post()
                .uri(NEWS_SERVICE)
                .bodyValue(newsRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Object getNews(NewsSearch newsSearch) {
        return webClientConnector.connectWebClient(NEWS_ROUTE)
                .post()
                .uri(NEWS_SERVICE + "/get")
                .bodyValue(newsSearch)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void updateNews(NewsRequest newsRequest, Integer newId) {
        newsRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(NEWS_ROUTE)
                .put()
                .uri(NEWS_SERVICE + "/" + newId)
                .bodyValue(newsRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableNews(Integer newId) {
        webClientConnector.connectWebClient(NEWS_ROUTE)
                .delete()
                .uri(NEWS_SERVICE + "/" + newId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
