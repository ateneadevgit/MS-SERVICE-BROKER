package com.fusm.servicebroker.servicebroker.model.ms_catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCatalogItem {

    private String keyWord;

}