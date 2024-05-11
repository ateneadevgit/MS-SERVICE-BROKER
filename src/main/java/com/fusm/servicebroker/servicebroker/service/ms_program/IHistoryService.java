package com.fusm.servicebroker.servicebroker.service.ms_program;

import org.springframework.stereotype.Service;

@Service
public interface IHistoryService {

    Object getHistorySpecific(Integer programId, Integer moduleId);
    Object gitHistoricByType(Integer programId, Integer moduleId, Integer typeId);

}
