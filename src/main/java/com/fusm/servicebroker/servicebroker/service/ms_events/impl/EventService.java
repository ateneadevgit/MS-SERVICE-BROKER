package com.fusm.servicebroker.servicebroker.service.ms_events.impl;

import com.fusm.servicebroker.servicebroker.model.ms_events.EventGeneralRequest;
import com.fusm.servicebroker.servicebroker.model.ms_events.EventSearch;
import com.fusm.servicebroker.servicebroker.service.GlobalService;
import com.fusm.servicebroker.servicebroker.service.ms_events.IEventService;
import com.fusm.servicebroker.servicebroker.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Value("${ms-event.complete-path}")
    private String EVENT_ROUTE;

    @Value("${ms-event.event.path}")
    private String EVENT_SERVICE;

    @Autowired
    private GlobalService globalService;


    @Override
    public Object getEvents(EventSearch eventSearch) {
        eventSearch.setRoleId(globalService.accessGlobalUserData().getRole());
        eventSearch.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        eventSearch.setProgramId((globalService.accessGlobalUserData().getProgramId() != null) ?
                globalService.accessGlobalUserData().getProgramId() : null);
        eventSearch.setFacultyId((globalService.accessGlobalUserData().getFaculty() != null) ?
                globalService.accessGlobalUserData().getFaculty() : eventSearch.getFacultyId());
        return webClientConnector.connectWebClient(EVENT_ROUTE)
                .post()
                .uri(EVENT_SERVICE + "/get")
                .bodyValue(eventSearch)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Override
    public void createGeneralEvent(EventGeneralRequest eventRequest) {
        eventRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        eventRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        webClientConnector.connectWebClient(EVENT_ROUTE)
                .post()
                .uri(EVENT_SERVICE + "/general")
                .bodyValue(eventRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void createPersonalEvent(EventGeneralRequest eventRequest) {
        eventRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        eventRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        eventRequest.setUserId(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(EVENT_ROUTE)
                .post()
                .uri(EVENT_SERVICE + "/personal")
                .bodyValue(eventRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void updateGeneralEvent(EventGeneralRequest eventGeneralRequest, Integer eventId, Boolean isPersonal) {
        eventGeneralRequest.setCreatedBy(globalService.accessGlobalUserData().getEmail());
        eventGeneralRequest.setRoleId(globalService.accessGlobalUserData().getRole());
        eventGeneralRequest.setUserId(globalService.accessGlobalUserData().getEmail());
        webClientConnector.connectWebClient(EVENT_ROUTE)
                .put()
                .uri(EVENT_SERVICE + "/" + eventId + "/is-personal/" + isPersonal)
                .bodyValue(eventGeneralRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public void disableEvent(Integer eventId, Boolean isPersonal) {
        webClientConnector.connectWebClient(EVENT_ROUTE)
                .delete()
                .uri(EVENT_SERVICE + "/" + eventId + "/is-personal/" + isPersonal)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
