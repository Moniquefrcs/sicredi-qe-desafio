package com.sicredi.api.clients;

import com.sicredi.api.specs.ApiSpecs;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response login(Map<String, Object> payload) {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .body(payload)
                .when()
                .post("/auth/login");
    }

    public Response me(String token) {
        return given()
                .spec(ApiSpecs.authenticatedRequest(token))
                .when()
                .get("/auth/me");
    }

    public Response products(String token) {
        return given()
                .spec(ApiSpecs.authenticatedRequest(token))
                .when()
                .get("/auth/products");
    }

    public Response productsWithoutToken() {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .when()
                .get("/auth/products");
    }
}
