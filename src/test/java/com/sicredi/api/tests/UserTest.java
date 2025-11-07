package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest {

    @Test
    @DisplayName("Deve acessar o perfil do usuário autenticado com sucesso")
    @Description("GET /auth/me - Perfil com token válido")
    void deveAcessarPerfilComTokenValido() {

        String token = AuthUtils.getToken();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/me")
                .then()
                .extract()
                .response();

        assertEquals(200, response.statusCode(), "A requisição deveria retornar 200 OK");
        assertNotNull(response.jsonPath().getString("username"));
        assertNotNull(response.jsonPath().getString("email"));
    }
}
