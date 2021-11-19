package com.example.petstore.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Schema(name = "Pet")
@Entity
public class Pet {
    @Schema(required = true, description = "Pet id")
    @Id @GeneratedValue private Long id;

    public Pet(String petName, String petAge, Long petTypeId) {
        this.petName = petName;
        this.petAge = petAge;
        this.petTypeId = petTypeId;
    }

    private String petName;

    private String petAge;

    private Long petTypeId;

    public Pet() {

    }

    public Pet(Long id, String petName, String petAge, Long petTypeId) {
        this.id = id;
        this.petName = petName;
        this.petAge = petAge;
        this.petTypeId = petTypeId;
    }

    public Long getId() {
        return id;
    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Long getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Long petTypeId) {
        this.petTypeId = petTypeId;
    }
}
