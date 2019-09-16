package com.sherwin.controller;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

/**
 *
 */
@OpenAPIDefinition(
        info = @Info(title = "The most awesome CRUD in the history"
        ,version = "1.0"
        ,contact = @Contact(
                name = "Victor Orozco",
                email = "vorozco@nabenik.com",
                url = "https://vorozco.com"
        ))
)
@ApplicationPath("/data")
@ApplicationScoped
public class DemocrudRestApplication extends Application {
}
