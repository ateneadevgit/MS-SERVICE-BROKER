package com.fusm.servicebroker.servicebroker.service.ms_news;

import com.fusm.servicebroker.servicebroker.model.ms_news.NewsRequest;
import com.fusm.servicebroker.servicebroker.model.ms_news.NewsSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INewsService {

    void createNew(NewsRequest newsRequest);
    Object getNews(NewsSearch newsSearch);
    void updateNews(NewsRequest newsRequest, Integer newId);
    void disableNews(Integer newId);

}
