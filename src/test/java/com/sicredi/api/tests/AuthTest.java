package com.sicredi.api.tests;

import com.sicredi.api.utils.AuthUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.RestAssured.given;

public class AuthTest {

    @Test
    @DisplayName("Deve realizar login e capturar o token de autenticação com sucesso")
    public void testLoginAuth() {

        // Define a URL base da API (dummy json usada no desafio)
        RestAssured.baseURI = "https://dummyjson.com";

        // Corpo da requisição
        String body = """
            {
                "username": "emilys",
                "password": "emilyspass"
            }
            """;

        // Executa o POST /auth/login e extrai a resposta
        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200) // dummyjson retorna 200
                .extract()
                .response();

        // Primeiro tenta "accessToken", se não tiver tenta "token"
        String token = response.jsonPath().getString("accessToken");
        if (token == null || token.isEmpty()) {
            token = response.jsonPath().getString("token");
        }

        // Asserção para garantir que temos token
        assertNotNull(token, "O token não foi retornado na resposta!");

        // Log no console e armazenamento via AuthUtils
        System.out.println("✅ Token capturado: " + token);
        AuthUtils.setToken(token);
    }
}
