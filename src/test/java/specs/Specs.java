package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.core.Is.is;

public class Specs {
    static String firstName = "firstName",
            job = "job",
            email = "eve.holt@reqres.in",
            password = "cityslicka";

    public static RequestSpecification requestSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification requestSuccessfulLogin = with()
            .spec(requestSpec)
            .body("{\"email\": " + "\"" + email + "\"" + "," +
                    "\"password\": " + "\"" + password + "\"}");

    public static RequestSpecification requestUnsuccessfulLoginWithoutPass = with()
            .spec(requestSpec)
            .body("{\"email\": " + "\"" + email + "\"}");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseMissingPassword = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build()
            .body("error", is("Missing password"));


}
