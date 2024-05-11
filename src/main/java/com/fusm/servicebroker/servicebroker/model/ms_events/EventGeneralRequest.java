package com.fusm.servicebroker.servicebroker.model.ms_events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventGeneralRequest {

    private String tittle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startHour;
    private LocalTime endHour;
    private Integer repetition;
    private String feedback;
    private String eventUrl;
    private Integer eventType;
    private Integer facultyId;
    private Integer programId;
    private Integer roleId;
    private Boolean isAllDay;
    private String createdBy;
    private String userId;

}
