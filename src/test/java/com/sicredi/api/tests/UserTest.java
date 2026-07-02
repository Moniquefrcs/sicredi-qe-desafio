package com.sicredi.api.tests;

import com.sicredi.api.clients.AuthClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.specs.ApiSpecs;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("auth")
@Tag("users")
public class UserTest extends BaseTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    @Tag("smoke")
    @DisplayName("Deve acessar o perfil do usuario autenticado com sucesso")
    @Description("GET /auth/me - Perfil com token valido")
    void deveAcessarPerfilComTokenValido() {
        String token = AuthUtils.getToken();

        String username = authClient.me(token)
                .then()
                .spec(ApiSpecs.ok())
                .body(matchesJsonSchemaInClasspath("schemas/user.schema.json"))
                .extract()
                .jsonPath()
                .getString("username");

        assertNotNull(username, "Username nao deve ser nulo");
    }
}
