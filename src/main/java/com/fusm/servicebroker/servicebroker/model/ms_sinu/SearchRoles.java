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
public class SearchRoles {

    private List<Integer> roles;
}
