package com.qaautomation.api;

import com.qaautomation.api.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends ApiBaseTest {
    @Test
    public void verifyGetUser_Success() {

        given()
                .log().all()
                .when()
                .get("/users/1")
                .then()
                .log().all()
                .statusCode(200)
                //.body("name", equalTo("Leanne Graham"));
                .body("id", equalTo(1))
                .body("username", notNullValue())
                .body("email", containsString("@"));
    }
    @Test
    public void verifyGetUser_NotFound() {

        given()
                .log().all()
                .when()
                .get("/users/9999")
                .then()
                .log().all()
                .statusCode(404);
    }
    @Test
    public void verifyDeleteUser() {

        given()
                .log().all()
                .when()
                .delete("/users/1")
                .then()
                .log().all()
                .statusCode(200);
    }
    public void validateStatusCode(String endpoint, int expectedCode) {

        given()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedCode);
    }
    @Test
    public void verifyInvalidUser() {
        validateStatusCode("/users/9999", 404);
    }
    @Test
    public void verifyCreatePost() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Test Automation Post");
        requestBody.put("body", "Created using RestAssured");
        requestBody.put("userId", 1);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts");


        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 201);

        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title, "Test Automation Post");
    }

}
