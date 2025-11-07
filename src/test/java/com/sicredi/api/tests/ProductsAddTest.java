package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.anyOf;

public class ProductsAddTest extends BaseTest {

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    @Description("POST /products/add - Criação de produto")
    void deveCriarProdutoComSucesso() {

        String body = """
            {
              "title": "Perfume Oil",
              "description": "Mega Discount, Impression of A...",
              "price": 13,
              "discountPercentage": 8.4,
              "rating": 4.26,
              "stock": 65,
              "brand": "Impression of Acqua Di Gio",
              "category": "fragrances",
              "thumbnail": "https://i.dummyjson.com/data/products/11/thumnail.jpg"
            }
            """;

        given()
                .body(body)
                .when()
                .post("/products/add")
                .then()
                // aceita 200 ou 201 (documentação x comportamento real)
                .statusCode(anyOf(is(200), is(201)))
                .body("id", notNullValue())
                .body("title", equalTo("Perfume Oil"))
                .body("price", equalTo(13))
                .body("stock", equalTo(65));
    }
}