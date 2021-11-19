package com.example.petstore.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PetType {

    @Schema(required = true, description = "Pet Type Id")
    @Id @GeneratedValue private Long id;

    @Schema(required = true, description = "Pet Type Name")
    private String petTypeName;

    public PetType() {
    }

    public PetType( String petTypeName) {

        this.petTypeName = petTypeName;
    }

    public Long getId() {
        return id;
    }
//
//    public void setId(Long petTypeId) {
//        this.id = petTypeId;
//    }

    public String getPetTypeName() {
        return petTypeName;
    }

    public void setPetTypeName(String petTypeName) {
        this.petTypeName = petTypeName;
    }
}
