
package com.guatejug.controller;

import com.guatejug.model.Phrase;
import com.guatejug.repository.PhraseRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;


@Path("/phrases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhraseController {
    
    @Inject
    PhraseRepository phraseRepository;
    
    @GET
    @Operation(description = "Busca todas las frases celebres de Sherwin")
    @APIResponse(description = "A phrase list from PostgresSQL")
    public List<Phrase> listAll(
            @QueryParam("author") @DefaultValue("%") String author,
            @QueryParam("phrase") @DefaultValue("%") String phrase
    ){
        return phraseRepository.superFind("%"+author+"%",
                "%"+phrase+"%");
    }
    
    @GET
    @Path("/{phraseId:[0-9][0-9]*}")
    public Phrase find(@PathParam("phraseId") Long phraseId){
        return phraseRepository.findBy(phraseId);
    }
    
    @POST
    public Response create(@Valid Phrase phrase){
        phraseRepository.save(phrase);
        return Response.created(
                UriBuilder.fromResource(PhraseController.class)
                .path(phrase.getPhraseId().toString()).build()
        ).build();
        
    }
    @PUT
    @Path("/{phraseId:[0-9][0-9]*}")
    public Response update(@PathParam("phraseId") Long phraseId,
            @Valid Phrase phrase){
        Phrase updatedPhrase = phraseRepository.save(phrase);
        
        return Response.accepted(updatedPhrase).build();
    }
    
    @DELETE
    @Path("/{phraseId:[0-9][0-9]*}")
    public void delete(@PathParam("phraseId") Long phraseId){
        Phrase phrase = phraseRepository.findBy(phraseId);
        phraseRepository.attachAndRemove(phrase);
    }
}
