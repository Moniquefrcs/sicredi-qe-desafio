package com.sicredi.api.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    private static final String DEFAULT_BASE_URL = "https://dummyjson.com";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", DEFAULT_BASE_URL);

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
}
