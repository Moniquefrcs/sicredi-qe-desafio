package com.sicredi.api.clients;

import com.sicredi.api.specs.ApiSpecs;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsClient {

    public Response list() {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .when()
                .get("/products");
    }

    public Response findById(int id) {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .pathParam("id", id)
                .when()
                .get("/products/{id}");
    }

    public Response add(Map<String, Object> payload) {
        return given()
                .spec(ApiSpecs.defaultRequest())
                .body(payload)
                .when()
                .post("/products/add");
    }
}
