package tests;

import lombok.LombokUserData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.*;
import static org.hamcrest.Matchers.hasItem;

public class reqresTests {

    @Test
    public void postRequestSuccessfulLoginSpecs() {
        given()
                .spec(requestSuccessfulLogin)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec).body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void postRequestUnsuccessfulLoginSpecs() {
        given()
                .spec(requestUnsuccessfulLoginWithoutPass)
                .when()
                .post("/login")
                .then()
                .spec(responseMissingPassword);
    }

    @Test
    void checkUserNameByLombok() {
        LombokUserData data = given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(LombokUserData.class);

        assertEquals("George", data.getUser().getFirstName());
    }

    @Test
    void checkUserNameByGroovy() {

        given()
                .spec(requestSpec)
                .when()
                .get("/users/")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("data.findAll{it.first_name}.first_name.flatten().",
                        hasItem("George"));

    }
}
