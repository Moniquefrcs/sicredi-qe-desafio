package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.anyOf;

public class ProductByIdTest extends BaseTest {

    @Test
    @DisplayName("Deve retornar produto existente pelo ID")
    @Description("GET /products/{id} - Sucesso")
    void deveRetornarProdutoExistentePorId() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    @DisplayName("Deve retornar erro para produto inexistente")
    @Description("GET /products/{id} - Fluxo de exceção para ID inválido")
    void deveRetornarErroParaProdutoInexistente() {
        given()
                .pathParam("id", 0) // id 0 não existe
                .when()
                .get("/products/{id}")
                .then()
                // aceita qualquer 4xx: 400, 404, etc.
                .statusCode(greaterThanOrEqualTo(400))
                .statusCode(lessThan(500));
    }
}
