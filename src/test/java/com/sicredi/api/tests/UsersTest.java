package com.sicredi.api.tests;

import com.sicredi.api.clients.UsersClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.specs.ApiSpecs;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Tag("users")
public class UsersTest extends BaseTest {

    private final UsersClient usersClient = new UsersClient();

    @Test
    @Tag("smoke")
    @DisplayName("Deve retornar lista de usuarios para autenticacao")
    @Description("GET /users - Lista de usuarios disponiveis para login")
    void deveListarUsuarios() {
        usersClient.list()
                .then()
                .spec(ApiSpecs.ok())
                .body(matchesJsonSchemaInClasspath("schemas/users-list.schema.json"))
                .body("users", notNullValue())
                .body("users.size()", greaterThan(0))
                .body("users[0].id", notNullValue())
                .body("users[0].username", notNullValue())
                .body("users[0].password", notNullValue());
    }
}
