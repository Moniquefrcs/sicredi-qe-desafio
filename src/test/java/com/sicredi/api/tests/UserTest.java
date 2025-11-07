package com.sicredi.api.tests;

import com.sicredi.api.utils.AuthUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private static String token;

    @BeforeAll
    static void setup() {
        // Obtém o token armazenado pelo AuthTest
        token = AuthUtils.getToken();
        System.out.println("🪪 Token carregado para o teste: " + token);

        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    @DisplayName("Deve acessar o perfil do usuário autenticado com sucesso")
    void testAcessarPerfilComToken() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/me")
                .then()
                .extract()
                .response();

        System.out.println(response.prettyPrint());

        // Se o token for válido, dummyjson retorna 200
        assertEquals(200, response.statusCode(), "A requisição deveria retornar 200 OK");
    }
}