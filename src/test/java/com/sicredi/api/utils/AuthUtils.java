package com.sicredi.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUtils {

    private static final String DEFAULT_BASE_URL = "https://dummyjson.com";
    private static final String DEFAULT_USERNAME = "emilys";
    private static final String DEFAULT_PASSWORD = "emilyspass";

    private static String token;

    public static String getToken() {
        if (token == null || token.isBlank()) {
            token = loginAndGetToken();
        }

        return token;
    }

    public static String loginPayload(String username, String password) {
        return """
            {
                "username": "%s",
                "password": "%s"
            }
            """.formatted(username, password);
    }

    private static String loginAndGetToken() {
        if (RestAssured.baseURI == null || RestAssured.baseURI.isBlank()) {
            RestAssured.baseURI = System.getProperty("base.url", DEFAULT_BASE_URL);
        }

        String username = System.getProperty("api.username", DEFAULT_USERNAME);
        String password = System.getProperty("api.password", DEFAULT_PASSWORD);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginPayload(username, password))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String accessToken = response.jsonPath().getString("accessToken");
        String legacyToken = response.jsonPath().getString("token");
        String resolvedToken = accessToken != null && !accessToken.isBlank() ? accessToken : legacyToken;

        if (resolvedToken == null || resolvedToken.isBlank()) {
            throw new IllegalStateException("Falha ao obter token em /auth/login. Resposta: " + response.asString());
        }

        return resolvedToken;
    }
}
