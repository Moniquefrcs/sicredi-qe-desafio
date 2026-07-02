package com.sicredi.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public final class ApiSpecs {

    private ApiSpecs() {
    }

    public static RequestSpecification defaultRequest() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static RequestSpecification authenticatedRequest(String token) {
        return new RequestSpecBuilder()
                .addRequestSpecification(defaultRequest())
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public static ResponseSpecification ok() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification okOrCreated() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(201)))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification unauthorizedOrForbidden() {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(401), is(403)))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification clientError() {
        return new ResponseSpecBuilder()
                .expectStatusCode(greaterThanOrEqualTo(400))
                .expectStatusCode(lessThan(500))
                .expectContentType(ContentType.JSON)
                .build();
    }
}
