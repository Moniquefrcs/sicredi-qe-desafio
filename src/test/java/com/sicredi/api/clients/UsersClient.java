package com.sicredi.api.clients;

import com.sicredi.api.specs.ApiSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersClient {

    public Response list() {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .when()
                .get("/users");
    }
}
