package com.fusm.servicebroker.servicebroker.service.ms_workflow;

import com.fusm.servicebroker.servicebroker.model.ms_workflow.NifsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INifsService {

    void createNifsData(NifsRequest nifsRequest);
    void updateNifsData(NifsRequest nifsRequest, Integer nifId);
    void addSection(List<NifsRequest> nifsRequest, Integer nifId);
    void disableSection(Integer nifId);
    Object viewNifsData(Integer type);

}
