package com.sicredi.api.tests;

import com.sicredi.api.clients.AuthClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.specs.ApiSpecs;
import com.sicredi.api.utils.AuthUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Tag("auth")
@Tag("products")
public class AuthProductsTest extends BaseTest {

    private final AuthClient authClient = new AuthClient();

    @Test
    @Tag("smoke")
    @DisplayName("Deve listar produtos autenticados com token valido")
    @Description("GET /auth/products - Sucesso com token valido")
    void deveListarProdutosAutenticadosComSucesso() {
        String token = AuthUtils.getToken();

        authClient.products(token)
                .then()
                .spec(ApiSpecs.ok())
                .body(matchesJsonSchemaInClasspath("schemas/products-list.schema.json"))
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0))
                .body("products[0].id", notNullValue())
                .body("products[0].title", notNullValue());
    }

    @Test
    @Tag("regression")
    @DisplayName("Nao deve permitir acesso sem token")
    @Description("GET /auth/products - Fluxo de excecao sem Authorization")
    void naoDevePermitirAcessoSemToken() {
        authClient.productsWithoutToken()
                .then()
                .spec(ApiSpecs.unauthorizedOrForbidden());
    }

    @Test
    @Tag("regression")
    @DisplayName("Nao deve permitir acesso com token invalido")
    @Description("GET /auth/products - Fluxo de excecao com token invalido")
    void naoDevePermitirAcessoComTokenInvalido() {
        authClient.products("token-invalido")
                .then()
                .spec(ApiSpecs.unauthorizedOrForbidden());
    }
}
