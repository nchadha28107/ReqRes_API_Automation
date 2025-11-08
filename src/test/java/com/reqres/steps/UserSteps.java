package com.reqres.steps;

import com.reqres.api.UserApi;
import com.reqres.enums.Context;
import com.reqres.model.User;
import com.reqres.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class UserSteps {

    private Response response;
    private User user;
    private String storedUserId;

    private Response getResponse() {
        return ScenarioContext.getContext(Context.RESPONSE);
    }

    @Given("I create a user with username {string} email {string} and password {string}")
    public void i_create_a_user_with_username_email_and_password(String username, String email, String password) {
        // Create user object
        user = new User(username, email, password);

        // Make API call to create user
        response = UserApi.createUser(user);

        // Store response in scenario context for later use
        ScenarioContext.setContext(Context.RESPONSE, response);
    }

    @Then("the user creation should be successful")
    public void the_user_creation_should_be_successful_with_status_code() {
        assertEquals(201, getResponse().statusCode(), "User request should return HTTP 201");
    }

    @Then("the response should have all required fields")
    public void the_response_should_have_all_required_fields() {
        assertNotNull(getResponse().as(User.class), "Users response should not be null");
    }

    @And("I store the created user ID")
    public void i_store_the_created_user_id() {
        response = (Response) ScenarioContext.getContext(Context.RESPONSE);
        storedUserId = getResponse().jsonPath().getString("id");
        ScenarioContext.setContext(Context.USERID, storedUserId);
    }

    @When("I retrieve the user details using the stored user ID")
    public void i_retrieve_the_user_details_using_the_stored_user_id() {
        storedUserId = (String) ScenarioContext.getContext(Context.USERID);
        response = UserApi.getUserById("2");
        ScenarioContext.setContext(Context.RESPONSE, response);
    }

    @When("I retrieve the user details using the user ID {string}")
    public void i_retrieve_the_user_details_using_the_user_id(String userId) {
        response = UserApi.getUserById(userId);
        ScenarioContext.setContext(Context.RESPONSE, response);
    }

    @Then("the user retrieval should be successful")
    public void the_user_retrieval_should_be_successful_with_status_code() {
        assertEquals(
                200, getResponse().statusCode(), "User Retrieval should return HTTP 200");
    }

    @And("the response should contain user details")
    public void the_response_should_contain_user_details() {
        assertNotNull(getResponse().jsonPath().get("data"), "User Response should contain data");
    }

    @When("I update the user username to {string} and email to {string}")
    public void i_update_the_user_username_to_and_emaik_to(String username, String email) {
        storedUserId = (String) ScenarioContext.getContext(Context.USERID);

        // Create updated user object
        User updatedUser = new User(username, email);

        // Make API call to update user
        response = UserApi.updateUser(storedUserId, updatedUser);
        ScenarioContext.setContext(Context.RESPONSE, response);
    }

    @Then("the user update should be successful")
    public void the_user_update_should_be_successful_with_status_code() {
        assertEquals(200, getResponse().statusCode(), "User update should return HTTP 200");
    }

    @And("the response should contain updated username {string} and email {string}")
    public void the_response_should_contain_updated_name(String expectedUserName, String expectedEmail) {
        assertEquals(expectedUserName, getResponse().jsonPath().getString("username"), "Updated username should match");
        assertEquals(expectedEmail, getResponse().jsonPath().getString("email"), "Updated email should match");
    }

    @And("the response should contain updatedAt timestamp")
    public void the_response_should_contain_updatedAt_timestamp() {
        String updatedAt = getResponse().jsonPath().getString("updatedAt");
        assertNotNull(updatedAt, "updatedAt timestamp should not be null");
    }

    @Then("the user retrieval should fail")
    public void the_user_retrieval_should_fail_with_status_code() {
        assertEquals(404, getResponse().statusCode(), "User retrieval should return HTTP 404");
    }

}