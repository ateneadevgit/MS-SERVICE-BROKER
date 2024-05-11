package com.fusm.servicebroker.servicebroker.model.ms_eportafolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRequest {

    private Boolean isFavorite;
    private String createdBy;

}