package com.guatejug.controller;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @Inject
    @ConfigProperty(name = "jhome", defaultValue = "The place where duke lives")
    String javaHome;
    
    int count = 0;

    @GET
    @Retry(delay = 1000, jitter = 500, maxRetries = 30)
    public String sayHello() throws Exception{
        
        if(++count % 15 != 0){
            System.out.println("Falling down");
            throw new Exception();
        }
        
        return "Hello World " + count;
    }

    @GET
    @Path("/javahome")
    public String getJavaHome(){
        return javaHome;
    }
}
