package com.fusm.servicebroker.servicebroker.configuration;

import com.fusm.servicebroker.servicebroker.model.GlobalData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public GlobalData globalData(){
        return new GlobalData();
    }

}
