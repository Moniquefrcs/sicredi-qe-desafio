package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ProductsAddTest extends BaseTest {

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    @Description("POST /products/add - Criacao de produto")
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
              "thumbnail": "https://i.dummyjson.com/data/products/11/thumbnail.jpg"
            }
            """;

        given()
                .body(body)
                .when()
                .post("/products/add")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("id", notNullValue())
                .body("title", equalTo("Perfume Oil"))
                .body("price", equalTo(13))
                .body("stock", equalTo(65));
    }
}
