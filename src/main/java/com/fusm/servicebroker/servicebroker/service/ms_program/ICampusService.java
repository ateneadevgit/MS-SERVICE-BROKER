package com.fusm.servicebroker.servicebroker.service.ms_program;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICampusService {

    List<Integer> getCampusByProgram(Integer programId);

}
