package org.acme;

import com.example.petstore.entities.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetResourceTest {

    @Test
    public void petGetALlEndpoint() {
        given()
                .when().get("/v1/pets")
                .then()
                .statusCode(200);


    }

    @Test
    public void petGetIdEndpoint() {
        int petId =3;
        given()
                .pathParam("petId", petId)
                .when().get("/v1/pets/{petId}")
                .then()
                .statusCode(200)
                .body(
                        "id",equalTo(petId),
                        "petAge",equalTo("10"),
                        "petName",equalTo("Jenny"),
                        "petTypeId",equalTo(1)


                );


    }

    @Test
    public void petPostEndpoint() {
        given()
                .contentType(ContentType.JSON).body(new Pet(6L,"Phess","10",2L))
                .when().post("/v1/pets")
                .then()
                .statusCode(200)
                .body(
                        "petAge",equalTo("10"),
                        "petName",equalTo("Phess"),
                        "petTypeId",equalTo(2)
                );
    }

    @Test
    public void petPutEndpoint() {
        int petId = 3;

        given()
                .contentType(ContentType.JSON).body(new  Pet(3L,"Example","10",2L))
                .pathParam("petId",petId)
                .when().put("/v1/pets/{petId}")
                .then()
                .statusCode(200)
                .body(
                        "id",equalTo(petId),
                        "petAge",equalTo("10"),
                        "petName",equalTo("Example"),
                        "petTypeId",equalTo(2)

                ).log().all();
    }

    @Test
    public void petDeleteEndpoint() {
        int petId = 4;
        given()
                .pathParam("petId",petId)
                .when().delete("/v1/pets/{petId}")
                .then()
                .statusCode(200)
                .body(
                        is("Deleted successfully")
                ).log().all();
    }

}