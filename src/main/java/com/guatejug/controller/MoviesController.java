package com.guatejug.controller;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MoviesController {
    
    @GET
    @Timeout(1000)
    @Fallback(fallbackMethod = "findFallbackMovies")
    @Timed(name = "moviesDelay",
            description = "Tiempo de respuesta",
            unit = MetricUnits.SECONDS ,
            absolute = true)
    public List<String> findMovies() throws Exception{
        String foo =null; System.out.println(foo.toString());
        return Arrays.asList("Black panther",
                "Avengers Endgame",
                "Spiderman far from home");
    }
    
    @Counted(description = "badMovies")
    public List<String> findFallbackMovies(){
        return Arrays.asList("Superman","Batman vs. Superman", "Justice League");
    }
    
    @Gauge(unit = "BuenosChistes",name = "chistesDePeliculas", absolute = true)
    public long getDatabases () {
         Random r = new Random();
        return r.nextInt(5000); //Any value
    }
    
}
