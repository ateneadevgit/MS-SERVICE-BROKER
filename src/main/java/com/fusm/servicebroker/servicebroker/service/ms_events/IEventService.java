package com.fusm.servicebroker.servicebroker.service.ms_events;

import com.fusm.servicebroker.servicebroker.model.ms_events.EventGeneralRequest;
import com.fusm.servicebroker.servicebroker.model.ms_events.EventSearch;
import org.springframework.stereotype.Service;

@Service
public interface IEventService {

    Object getEvents(EventSearch eventSearch);
    void createGeneralEvent(EventGeneralRequest eventRequest);
    void createPersonalEvent(EventGeneralRequest eventRequest);
    void updateGeneralEvent(EventGeneralRequest eventGeneralRequest, Integer eventId, Boolean isPersonal);
    void disableEvent(Integer eventId, Boolean isPersonal);

}
