package com.sicredi.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUtils {

    private static String token;

    /**
     * Retorna o token de autenticação.
     * Se ainda não existir, faz o login em /auth/login e guarda em memória.
     */
    public static String getToken() {
        if (token == null || token.isEmpty()) {
            token = loginAndGetToken();
        }
        return token;
    }

    /**
     * Realiza o login em POST /auth/login e devolve o token (campo "accessToken" ou "token").
     */
    private static String loginAndGetToken() {

        // Garante baseURI caso não tenha sido setado por BaseTest
        if (RestAssured.baseURI == null || RestAssured.baseURI.isEmpty()) {
            RestAssured.baseURI = "https://dummyjson.com";
        }

        String body = """
            {
                "username": "emilys",
                "password": "emilyspass"
            }
            """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                // não travamos o teste no status aqui, apenas extraímos
                .extract()
                .response();

        // tenta primeiro accessToken, depois token (para ser compatível com a doc atual)
        String accessToken = response.jsonPath().getString("accessToken");
        String token = accessToken;

        if (token == null || token.isEmpty()) {
            token = response.jsonPath().getString("token");
        }

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Falha ao obter token em /auth/login. Resposta: " + response.asString());
        }

        return token;
    }
}
