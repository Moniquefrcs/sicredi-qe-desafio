package com.sicredi.api.tests;

import com.sicredi.api.clients.AuthClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.payloads.LoginPayloadFactory;
import com.sicredi.api.specs.ApiSpecs;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("auth")
public class AuthTest extends BaseTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    @Tag("smoke")
    @DisplayName("Deve realizar login com sucesso e retornar um token valido")
    @Description("POST /auth/login - Login com credenciais validas")
    void loginComSucesso() {
        String token = AuthUtils.getToken();

        assertNotNull(token, "Token nao deve ser nulo");
        assertFalse(token.isBlank(), "Token nao deve ser vazio");
    }

    @Test
    @Tag("regression")
    @DisplayName("Nao deve autenticar com credenciais invalidas")
    @Description("POST /auth/login - Fluxo de excecao com senha invalida")
    void naoDeveAutenticarComCredenciaisInvalidas() {
        authClient.login(LoginPayloadFactory.invalidPassword())
                .then()
                .spec(ApiSpecs.clientError())
                .body("message", not(emptyOrNullString()));
    }
}
