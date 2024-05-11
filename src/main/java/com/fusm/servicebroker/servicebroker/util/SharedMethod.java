package com.fusm.servicebroker.servicebroker.util;

import com.fusm.servicebroker.servicebroker.exception.GlobalCustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SharedMethod {

    public String getTokenValue(HttpHeaders headers) {
        String token = headers.getFirst("Authorization");
        if (token == null) throw new GlobalCustomException(HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN);
        return token.replace("Bearer ", "");
    }

    public Boolean validateUri(List<String> permissionList, String path) {
        boolean found = false;
        for (String permissionPath : permissionList) {
            if (path.contains(permissionPath)) {
                found = true;
                break;
            }
        }
        return found;
    }

}
