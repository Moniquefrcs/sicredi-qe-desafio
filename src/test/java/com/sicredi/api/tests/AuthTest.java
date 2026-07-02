package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthTest extends BaseTest {

    @Test
    @DisplayName("Deve realizar login com sucesso e retornar um token valido")
    @Description("POST /auth/login - Login com credenciais validas")
    void loginComSucesso() {
        String token = AuthUtils.getToken();

        assertNotNull(token, "Token nao deve ser nulo");
        assertFalse(token.isBlank(), "Token nao deve ser vazio");
    }

    @Test
    @DisplayName("Nao deve autenticar com credenciais invalidas")
    @Description("POST /auth/login - Fluxo de excecao com senha invalida")
    void naoDeveAutenticarComCredenciaisInvalidas() {
        given()
                .body(AuthUtils.loginPayload("emilys", "senha-invalida"))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(400)
                .body("message", not(emptyOrNullString()));
    }
}
