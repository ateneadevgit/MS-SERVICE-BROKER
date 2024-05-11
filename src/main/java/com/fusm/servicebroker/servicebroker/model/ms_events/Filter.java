package com.fusm.servicebroker.servicebroker.model.ms_events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {

    private Integer programId;
    private Integer facultyId;
    private List<Integer> types;

}
