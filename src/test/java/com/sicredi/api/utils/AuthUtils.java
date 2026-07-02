package com.sicredi.api.utils;

import com.sicredi.api.clients.AuthClient;
import com.sicredi.api.payloads.LoginPayloadFactory;
import com.sicredi.api.specs.ApiSpecs;
import io.restassured.response.Response;

public class AuthUtils {

    private static final AuthClient AUTH_CLIENT = new AuthClient();

    private static String token;

    public static String getToken() {
        if (token == null || token.isBlank()) {
            token = loginAndGetToken();
        }

        return token;
    }

    private static String loginAndGetToken() {
        Response response = AUTH_CLIENT.login(LoginPayloadFactory.valid())
                .then()
                .spec(ApiSpecs.ok())
                .extract()
                .response();

        String accessToken = response.jsonPath().getString("accessToken");
        String legacyToken = response.jsonPath().getString("token");
        String resolvedToken = accessToken != null && !accessToken.isBlank() ? accessToken : legacyToken;

        if (resolvedToken == null || resolvedToken.isBlank()) {
            throw new IllegalStateException("Falha ao obter token em /auth/login. Resposta: " + response.asString());
        }

        return resolvedToken;
    }
}
