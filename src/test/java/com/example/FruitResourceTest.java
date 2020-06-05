package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class FruitResourceTest {

    @InjectMock
    @RestClient
    FruitClient fruitClient;

    @Test
    public void testGetFruit() {
        given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when().get("/fruits")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddFruit() {
        given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .body(new Fruit(BigDecimal.valueOf(5.6)))
                .post("/fruits")
                .then()
                .statusCode(204);
        Mockito.verify(fruitClient, Mockito.times(1)).delegate(any(Fruit.class));
    }

}
