package com.example.petstore;


import com.example.petstore.entities.Pet;
import com.example.petstore.entities.PetType;
import com.example.petstore.repositories.PetRepository;
import com.example.petstore.repositories.PetTypeRepository;
import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Startup
@Singleton
public class OnStart {

    @Inject
    PetRepository petRepository;

    @Inject
    PetTypeRepository petTypeRepository;

    @PostConstruct
    @Transactional
    public void onStart() {

        PetType petType1 = new PetType("Dog");
        PetType petType2 = new PetType("Bird");

        petTypeRepository.persist(petType1,petType2);

        Pet p1 = new Pet("Jenny","10",1L);
        Pet p2 = new Pet("Sam","5",1L);
        petRepository.persist(p1,p2);

    }



}