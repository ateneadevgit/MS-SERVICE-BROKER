package com.fusm.servicebroker.servicebroker.model.ms_sinu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDirectory {

    private String name;
    private List<Integer> facultyId;
    private List<Integer> campusId;

}
