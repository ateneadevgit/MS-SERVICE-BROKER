package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.servicebroker.servicebroker.model.ms_program.EvaluateObjectRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ObjectTempRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.ObjectToReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IObjectTempService {

    void createdObjectTemp(ObjectTempRequest objectTempRequest, Integer programId) throws JsonProcessingException;
    void evaluateObject(EvaluateObjectRequest evaluateObjectRequest, Integer programId) throws JsonProcessingException;
    void sendObjectToReview(ObjectToReview objectToReview);
    Object getObjectTemp(Integer programId, Integer moduleId);
    void updateObjectTemp(ObjectTempRequest objectTempRequest, Integer objectId);
    Integer getObjectNumberCredits(Integer moduleId, Integer programId) throws JsonProcessingException;
    Object getCores(Integer moduleId, Integer programId) throws JsonProcessingException;
    Object getSubjectsByCore(Integer coreId, Integer moduleId, Integer programId) throws JsonProcessingException;

}
