package com.example.petstore.resources;

import com.example.petstore.entities.Pet;
import com.example.petstore.repositories.PetRepository;
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

@Path("/v1/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PetResource {
    @Inject
    PetRepository petRepository;

    @Inject
    PetTypeRepository petTypeRepository;

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })

    @GET
    public Response getPetList() {
        List<Pet> petList = petRepository.listAll();
        return Response.ok(petList).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })

    @GET
    @Path("{petId}")
    public Response getPet(@PathParam("petId") Long petId) {
        Pet pet = petRepository.findById(petId);
        return Response.ok(pet).build();
    }

    @PUT
    @Path("{petId}")
    @Transactional
    public Response updatePet(@PathParam("petId") Long petId, Pet petObj) {
        Pet pet = petRepository.findById(petId);
        if (pet == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        pet.setPetAge(petObj.getPetAge());
        pet.setPetName(petObj.getPetName());
        pet.setPetTypeId(petObj.getPetTypeId());

        petRepository.persist(pet);
        return Response.ok(pet).build();
    }

    @POST
    @Transactional
    public Response setPet(Pet petObj) {
        petRepository.persist(petObj);
        return Response.ok(petObj).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Deleted the pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })

    @DELETE
    @Transactional
    @Path("{petId}")
    public Response deletePet(@PathParam("petId") Long petId) {
        petRepository.deleteById(petId);
        return Response.ok("Deleted successfully").build();
    }
}
