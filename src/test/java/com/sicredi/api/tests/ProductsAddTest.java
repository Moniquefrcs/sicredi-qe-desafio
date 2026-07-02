package com.sicredi.api.tests;

import com.sicredi.api.clients.ProductsClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.payloads.ProductPayloadFactory;
import com.sicredi.api.specs.ApiSpecs;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("products")
public class ProductsAddTest extends BaseTest {

    private final ProductsClient productsClient = new ProductsClient();

    @Test
    @Tag("regression")
    @DisplayName("Deve criar um produto com sucesso")
    @Description("POST /products/add - Criacao de produto")
    void deveCriarProdutoComSucesso() {
        productsClient.add(ProductPayloadFactory.validProduct())
                .then()
                .spec(ApiSpecs.okOrCreated())
                .body("id", notNullValue())
                .body("title", equalTo("Perfume Oil"))
                .body("price", equalTo(13))
                .body("stock", equalTo(65));
    }
}
