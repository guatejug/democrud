
package com.sherwin.controller;

import com.sherwin.dto.PhraseDTO;
import com.sherwin.model.Phrase;
import com.sherwin.repository.PhraseRepository;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/phrases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhraseController {
    
    @Inject
    PhraseRepository phraseRepository;
    
    @GET
    @Operation(description = "Busca todas las frases celebres de Sherwin")
    @APIResponse(description = "Un listado de frases desde PostgreSQL")
    public List<PhraseDTO> listAll(
            @QueryParam("author") @DefaultValue("%") String author,
            @QueryParam("phrase") @DefaultValue("%") String phrase
    ){
        return phraseRepository.fasterFind("%"+author+"%",
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
