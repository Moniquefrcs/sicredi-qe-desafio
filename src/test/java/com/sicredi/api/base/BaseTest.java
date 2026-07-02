package com.sicredi.api.base;

import com.sicredi.api.specs.ApiSpecs;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    private static final String DEFAULT_BASE_URL = "https://dummyjson.com";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", DEFAULT_BASE_URL);
        RestAssured.requestSpecification = ApiSpecs.defaultRequest();
    }
}
