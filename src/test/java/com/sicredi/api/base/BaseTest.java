package com.sicredi.api.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        // URL base da API do desafio (ajuste se o Sicredi tiver fornecido outra)
        RestAssured.baseURI = "https://dummyjson.com";
    }
}
