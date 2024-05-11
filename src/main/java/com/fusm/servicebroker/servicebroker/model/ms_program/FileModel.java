package com.fusm.servicebroker.servicebroker.model.ms_program;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {

    @NonNull
    @NotEmpty
    private String fileContent;

    @NonNull
    @NotEmpty
    private String fileExtension;

}
