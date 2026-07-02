package com.sicredi.api.tests;

import com.sicredi.api.base.BaseTest;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AuthProductsTest extends BaseTest {

    @Test
    @DisplayName("Deve listar produtos autenticados com token valido")
    @Description("GET /auth/products - Sucesso com token valido")
    void deveListarProdutosAutenticadosComSucesso() {
        String token = AuthUtils.getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/products")
                .then()
                .statusCode(200)
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0))
                .body("products[0].id", notNullValue())
                .body("products[0].title", notNullValue());
    }

    @Test
    @DisplayName("Nao deve permitir acesso sem token")
    @Description("GET /auth/products - Fluxo de excecao sem Authorization")
    void naoDevePermitirAcessoSemToken() {
        given()
                .when()
                .get("/auth/products")
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }

    @Test
    @DisplayName("Nao deve permitir acesso com token invalido")
    @Description("GET /auth/products - Fluxo de excecao com token invalido")
    void naoDevePermitirAcessoComTokenInvalido() {
        given()
                .header("Authorization", "Bearer token-invalido")
                .when()
                .get("/auth/products")
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }
}
