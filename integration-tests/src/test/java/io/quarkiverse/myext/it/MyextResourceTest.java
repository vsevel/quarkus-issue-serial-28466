package io.quarkiverse.myext.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MyextResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/myext")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }
}
