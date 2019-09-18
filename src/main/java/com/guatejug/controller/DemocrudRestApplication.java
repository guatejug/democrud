package com.guatejug.controller;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@OpenAPIDefinition(
        info = @Info(title = "The most awesome CRUD in the history"
        ,version = "1.0"
        ,contact = @Contact(
                name = "Victor Orozco  - Jorge Cajas",
                email = "vorozco@nabenik.com, jac.mota@gmail.com",
                url = "https://www.guate-jug.net"
        ))
)
@ApplicationPath("/data")
@ApplicationScoped
public class DemocrudRestApplication extends Application {
}
