package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class UsersTest extends BaseTest {

    @Test
    @DisplayName("Deve retornar lista de usuarios para autenticacao")
    @Description("GET /users - Lista de usuarios disponiveis para login")
    void deveListarUsuarios() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("users", notNullValue())
                .body("users.size()", greaterThan(0))
                .body("users[0].id", notNullValue())
                .body("users[0].username", notNullValue())
                .body("users[0].password", notNullValue());
    }
}
