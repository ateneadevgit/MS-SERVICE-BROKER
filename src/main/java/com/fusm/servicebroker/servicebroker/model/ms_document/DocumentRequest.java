package com.fusm.servicebroker.servicebroker.model.ms_document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest {

    private String idUser;
    private String documentExtension;
    private String documentVersion;
    private String documentBytes;

}
