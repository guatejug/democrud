package com.guatejug.controller;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Retry;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {
    
    int cuenta = 0;

    @GET
    @Retry(delay = 1000, jitter = 500, maxRetries = 30)
    public String sayHello() throws Exception{
        
        if(++cuenta % 15 != 0){
            System.out.println("Ay! me caí");
            throw new Exception();
        }
        
        return "Hello World " + cuenta;
    }
}
