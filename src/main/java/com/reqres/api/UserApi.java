package com.reqres.api;

import com.reqres.model.User;
import com.reqres.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {

    private static final String baseUri = ConfigReader.getBaseUrl(System.getProperty("env", "qa"));
    private static final String createUserEndpoint = ConfigReader.getEndpoint("User", "create");
    private static final String getUserEndpoint = ConfigReader.getEndpoint("User", "get");
    private static final String updateUserEndpoint = ConfigReader.getEndpoint("User", "update");
    private static final String apiKey = ConfigReader.getEndpoint("api", "key");

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("x-api-key", apiKey)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static Response createUser(User user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .when()
                .post(createUserEndpoint);
    }

    public static Response getUserById(String userId) {
        return given()
                .spec(getRequestSpec())
                .when()
                .get(getUserEndpoint + userId);
    }

    public static Response updateUser(String userId, User user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .when()
                .put(updateUserEndpoint + userId);
    }
}