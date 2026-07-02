package com.sicredi.api.tests;

import com.sicredi.api.clients.HealthClient;
import com.sicredi.api.base.BaseTest;
import com.sicredi.api.specs.ApiSpecs;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

@Tag("health")
public class HealthCheckTest extends BaseTest {

    private final HealthClient healthClient = new HealthClient();

    @Test
    @Tag("smoke")
    @Description("Verifica se a API esta online (GET /test)")
    void testGetStatus() {
        healthClient.check()
                .then()
                .spec(ApiSpecs.ok())
                .body(notNullValue());
    }
}
