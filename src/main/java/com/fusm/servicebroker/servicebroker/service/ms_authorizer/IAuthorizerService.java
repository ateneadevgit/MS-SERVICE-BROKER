package com.fusm.servicebroker.servicebroker.service.ms_authorizer;

import com.fusm.servicebroker.servicebroker.model.ms_authorizer.RequestTokenModel;
import org.springframework.stereotype.Service;

@Service
public interface IAuthorizerService {

    Object refreshToken(RequestTokenModel requestTokenModel);

}
