package com.fusm.servicebroker.servicebroker.service;

import com.fusm.servicebroker.servicebroker.model.GlobalData;
import com.fusm.servicebroker.servicebroker.model.ms_authorizer.AuthUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalService {

    private GlobalData globalData;

    @Autowired
    public GlobalService(GlobalData globalData) {
        this.globalData = globalData;
    }

    public void modifyGlobalUserData(AuthUserModel authUserModel) {
        globalData.setAuthUserModel(authUserModel);
    }

    public AuthUserModel accessGlobalUserData() {
        return globalData.getAuthUserModel();
    }

}
