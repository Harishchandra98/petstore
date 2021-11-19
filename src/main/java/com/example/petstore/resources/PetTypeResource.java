package com.example.petstore.resources;

import com.example.petstore.entities.Pet;
import com.example.petstore.entities.PetType;
import com.example.petstore.repositories.PetTypeRepository;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("v1/pettype")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PetTypeResource {

    @Inject
    PetTypeRepository petTypeRepository;

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType")))
                    })

    @GET
    public Response getPetType() {
        List<PetType> petTypeList = petTypeRepository.listAll();
        return Response.ok(petTypeList).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet Type found for the id.") })

    @GET
    @Path("{petTypeId}")
    public Response getPetType(@PathParam("petTypeId") Long petTypeId) {
        PetType pet = petTypeRepository.findById(petTypeId);
        return Response.ok(pet).build();
    }

    @PUT
    @Path("{petId}")
    @Transactional
    public Response updatePetTYpe(@PathParam("petId") Long petId, PetType petObj) {
        PetType pet = petTypeRepository.findById(petId);
        if (pet == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        pet.setPetTypeName(pet.getPetTypeName());

        petTypeRepository.persist(pet);
        return Response.ok(pet).build();
    }

    @POST
    @Transactional
    public Response setPetType(PetType petObj) {
        petTypeRepository.persist(petObj);
        return Response.ok(petObj).build();
    }


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Successfully deleted the pet type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet type found for the id.") })

    @DELETE
    @Transactional
    @Path("{petTypeId}")
    public Response deletePetType(@PathParam("petTypeId") Long petTypeId) {

        if(  !petTypeRepository.deleteById(petTypeId))
                return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok("Deleted successfully").build();
    }
}
