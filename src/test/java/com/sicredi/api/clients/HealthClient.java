package com.sicredi.api.clients;

import com.sicredi.api.specs.ApiSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HealthClient {

    public Response check() {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .when()
                .get("/test");
    }
}
