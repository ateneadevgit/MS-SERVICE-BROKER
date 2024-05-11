package com.fusm.servicebroker.servicebroker.model.ms_events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSearch {

    private Integer facultyId;
    private Integer[] programId;
    private Integer roleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String createdBy;
    private Filter filter;

}
