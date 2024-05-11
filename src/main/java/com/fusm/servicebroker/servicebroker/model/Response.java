package com.fusm.servicebroker.servicebroker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private Integer status;
    private String message;
    private T data;

    public Response(HttpStatus httpStatus, T data) {
        super();
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
    }

}
