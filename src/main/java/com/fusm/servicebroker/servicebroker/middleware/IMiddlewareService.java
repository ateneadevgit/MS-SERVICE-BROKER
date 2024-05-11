package com.fusm.servicebroker.servicebroker.middleware;

import com.fusm.servicebroker.servicebroker.model.ms_authorizer.AuthUserModel;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.RequestTokenModel;
import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ValidatePermission;
import com.fusm.servicebroker.servicebroker.model.ms_security.BlackListModel;
import org.springframework.stereotype.Service;

@Service
public interface IMiddlewareService {
    Boolean getSecurityApproval(BlackListModel blackListModel);
    AuthUserModel getAuthorizerApproval(RequestTokenModel requestTokenModel);
    Boolean hasView(ValidatePermission validatePermission);
    Boolean hasWrite(ValidatePermission validatePermission);
    Boolean hasUpdate(ValidatePermission validatePermission);
    Boolean hasDelete(ValidatePermission validatePermission);
    void createLog(String request, String response);
    void allMiddlewares(String request, String method, boolean isHtml, String module, String token);
}
