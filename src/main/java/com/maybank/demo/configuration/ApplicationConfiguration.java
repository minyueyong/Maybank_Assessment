package com.maybank.demo.configuration;


import com.maybank.demo.aspect.LoggingMethodAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfiguration {


    @Bean
    public LoggingMethodAspect loggingAspect() {
        LoggingMethodAspect myAspect = new LoggingMethodAspect();
        // Configure properties of the aspect here
        return myAspect;
    }
}
