package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fusm.servicebroker.servicebroker.model.ms_program.TraceabilityRequest;
import org.springframework.stereotype.Service;

@Service
public interface ITraceabilityService {

    Object getProgramTraceability(Integer programId);
    void createTraceability(TraceabilityRequest traceabilityRequest, Integer programId);
    void updateTraceability(TraceabilityRequest traceabilityRequest, Integer programId);

}
