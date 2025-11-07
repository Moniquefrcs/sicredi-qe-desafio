package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseTest {

    @Test
    @DisplayName("Deve retornar lista de usuários para autenticação")
    @Description("GET /users - Lista de usuários disponíveis para login")
    void deveListarUsuarios() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                // Estrutura padrão do dummyjson: { "users": [...], "total":..., "skip":..., "limit":... }
                .body("users", notNullValue())
                .body("users.size()", greaterThan(0))
                .body("users[0].id", notNullValue())
                .body("users[0].username", notNullValue())
                .body("users[0].password", notNullValue());
    }
}
