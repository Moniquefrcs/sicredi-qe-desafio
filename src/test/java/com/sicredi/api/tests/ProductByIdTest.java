package com.sicredi.api.tests;

import com.sicredi.api.clients.ProductsClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.specs.ApiSpecs;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("products")
public class ProductByIdTest extends BaseTest {

    private final ProductsClient productsClient = new ProductsClient();

    @Test
    @Tag("smoke")
    @DisplayName("Deve retornar produto existente pelo ID")
    @Description("GET /products/{id} - Sucesso")
    void deveRetornarProdutoExistentePorId() {
        productsClient.findById(1)
                .then()
                .spec(ApiSpecs.ok())
                .body(matchesJsonSchemaInClasspath("schemas/product.schema.json"))
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    @Tag("regression")
    @DisplayName("Deve retornar erro para produto inexistente")
    @Description("GET /products/{id} - Fluxo de excecao para ID invalido")
    void deveRetornarErroParaProdutoInexistente() {
        productsClient.findById(0)
                .then()
                .spec(ApiSpecs.clientError());
    }
}
