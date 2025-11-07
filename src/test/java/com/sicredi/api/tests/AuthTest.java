package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthTest extends BaseTest {

    @Test
    @DisplayName("Deve realizar login com sucesso e retornar um token válido")
    @Description("POST /auth/login - Login com credenciais válidas")
    void loginComSucesso() {
        String token = AuthUtils.getToken();
        assertNotNull(token, "Token não deve ser nulo");
        System.out.println("✅ Token obtido: " + token);
    }
}

