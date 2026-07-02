package com.sicredi.api.payloads;

import java.util.Map;

public final class LoginPayloadFactory {

    private LoginPayloadFactory() {
    }

    public static Map<String, Object> valid() {
        return withCredentials(
                System.getProperty("api.username", "emilys"),
                System.getProperty("api.password", "emilyspass")
        );
    }

    public static Map<String, Object> invalidPassword() {
        return withCredentials("emilys", "senha-invalida");
    }

    public static Map<String, Object> withCredentials(String username, String password) {
        return Map.of(
                "username", username,
                "password", password
        );
    }
}
