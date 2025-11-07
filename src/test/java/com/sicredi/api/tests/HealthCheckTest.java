package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HealthCheckTest extends BaseTest {

    @Test
    @Description("Verifica se a API está online (GET /test)")
    void testGetStatus() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/test")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }
}
