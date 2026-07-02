package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

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
    @Description("GET /products/{id} - Fluxo de excecao para ID invalido")
    void deveRetornarErroParaProdutoInexistente() {
        given()
                .pathParam("id", 0)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(greaterThanOrEqualTo(400))
                .statusCode(lessThan(500));
    }
}
