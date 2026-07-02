package com.sicredi.api.tests;

import com.sicredi.api.clients.ProductsClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.specs.ApiSpecs;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Tag("products")
public class ProductsTest extends BaseTest {

    private final ProductsClient productsClient = new ProductsClient();

    @Test
    @Tag("smoke")
    @DisplayName("Deve listar todos os produtos")
    @Description("GET /products - Lista de produtos")
    void deveListarProdutos() {
        productsClient.list()
                .then()
                .spec(ApiSpecs.ok())
                .body(matchesJsonSchemaInClasspath("schemas/products-list.schema.json"))
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0))
                .body("products[0].id", notNullValue())
                .body("products[0].title", notNullValue());
    }
}
