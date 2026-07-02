package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class HealthCheckTest extends BaseTest {

    @Test
    @Description("Verifica se a API esta online (GET /test)")
    void testGetStatus() {
        given()
                .when()
                .get("/test")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }
}
