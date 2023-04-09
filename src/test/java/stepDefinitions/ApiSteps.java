package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import pages.ApiValidation;



public class ApiSteps extends ApiValidation {

    @Given("user given api url {string}")
    public void userGivenApiUrl(String url) {
        RestAssured.baseURI=url;


        System.out.println("user given api url " + url);
    }

    @Given("set api endpoint {string}")
    public void setApiEndpoint(String endpoint) {
        RestAssured.basePath= endpoint;
        //RestAssured.port=8080;

        System.out.println("set api endpoint " + endpoint);

    }

    @And("user creates new user with request body {string},{string},{string},{string}")
    public void userCreatesNewUserWithRequestBody(String name, String gender, String email, String status) {
        response= postMethod(name, gender, email, status);
        response.prettyPrint();

        System.out.println("user creates new user with request body " + name + " " + gender + " " + email + " " + status);

    }



    @Then("validate the status code {int}")
    public void validateTheStatusCode(int expectedstatusCode) {
        int actualstatusCode=response.getStatusCode();

        try {
            Assert.assertEquals(expectedstatusCode,actualstatusCode);

        }catch (AssertionFailedError ae){
            ae.printStackTrace();
        }

        System.out.println("Assertion successful");


    }

    @And("validate the userId is not null")
    public void validateTheUserIdIsNotNull() {
        int id = response.jsonPath().getInt("data.id");
        System.out.println(id);
        Assert.assertTrue("id is empty", id !=0);


    }

    @And("validate the user Name is {string}")
    public void validateTheUserNameIs(String expactedName) {
        String actualName= response.jsonPath().getString("data.name");
        System.out.println(actualName);
        Assert.assertEquals(expactedName, actualName);
        System.out.println("Assertion success :"+actualName);



    }

    @And("validate the user Gender is {string}")
    public void validateTheUserGenderIs(String gender) {
        String actualGender= response.jsonPath().getString("data.gender");
        System.out.println(actualGender);

        Assert.assertEquals(gender, actualGender);
        System.out.println("Assertion success :"+gender);


    }

    @And("validate the user Email is {string}")
    public void validateTheUserEmailIs(String email) {
        String actualEmail= response.jsonPath().getString("data.email");
        System.out.println(actualEmail);

        Assert.assertEquals(email, actualEmail);
        System.out.println("Assertion success :"+actualEmail);





    }

    @And("validate the user Status is {string}")
    public void validateTheUserStatusIs(String status) {

        String actualStatus= response.jsonPath().getString("data.status");
        System.out.println(actualStatus);

        Assert.assertEquals(status, actualStatus);
        System.out.println("Assertion success :"+actualStatus);

    }

    @Given("set api endpoint {string}{string}")
    public void setApiEndpoint(String endpoint, String userId) {
        RestAssured.basePath=endpoint+userId;
    }

    @And("Update the user with request body {string},{string},{string},{string}")
    public void updateTheUserWithRequestBody(String name, String gender, String email, String status) {
        response=putMethod(name,gender,email,status);
        response.prettyPrint();

    }

    /**
    * Post and comment scenario starts from here
     */

    @Given("user sets {string} post")
    public void userSetsPost(String endpointPost) {
        RestAssured.basePath=endpointPost;
    }

    int postId;
    @And("create a post with given userId and create one {string} and {string}")
    public void createAPostWithGivenUserIdAndCreateOneAnd(String title, String body) {
        response=postMethodCreate(title, body);
        response.prettyPrint();
        postId=response.jsonPath().get("data.id");
        System.out.println(postId);
    }



    @When("user sets {string} post and create one {string} using {string}, {string}, {string}, {string}")
    public void userSetsPostAndCreateOneUsing(String endpointComment, String comment, String userId, String name, String email, String commentBody) {
        endpointComment=endpointComment.replaceAll(userId, String.valueOf(postId));
        RestAssured.basePath=endpointComment;
        response=postMethodComment(comment,name,email);
        response.prettyPrint();


    }


    @Then("verify that comment created {string} {string}")
    public void verifyThatCommentCreated(String name, String email) {
        String actualName=response.jsonPath().get("data.name");
        String actualEmail=response.jsonPath().get("data.email");
        Assert.assertEquals(name, actualName);
        Assert.assertEquals(email, actualEmail);
        System.out.println("Assertion successful");

    }

    /**
     *
     * DELETE USER //////////////////////////////
     */

    @Given("user set endpoint {string} for {string} userid")
    public void userSetEndpointForUserid(String endpoint, String userId) {
        RestAssured.basePath= endpoint+userId;
    }

    @And("use DELETE request to delete specific user")
    public void useDELETERequestToDeleteSpecificUser() {
        response= deleteMethod();
        response.prettyPrint();

    }



    @Then("validate the response status code is {int}")
    public void validateTheResponseStatusCodeIs(int expectedCode) {
        int code=0;
        try {
            code= response.jsonPath().getInt("code");
            System.out.println("code = " + code);
        }catch (Exception i){
            i.printStackTrace();
        }

        try {
            Assert.assertEquals(code, expectedCode);
            System.out.println("Assertion successful for code");
        } catch (AssertionError ae) {
            ae.printStackTrace();
        }

    }

    @Then("try to get the user data and validate response code is {int}")
    public void tryToGetTheUserDataAndValidateResponseCodeIs(int expectedCode) {
        response = deleteMethod();
        int code = response.jsonPath().getInt("code");
        System.out.println(code);
        try {
            Assert.assertEquals(code, expectedCode);
            System.out.println("Assertion successful for code");
        } catch (AssertionError ae) {
            ae.printStackTrace();
        }

    }

    @Then("validate with response message {string}")
    public void validateWithResponseMessage(String expectedMessage) {
        response.prettyPrint();
        String message= response.jsonPath().get("data.message");
        System.out.println("message = " + message);

       try {
           Assert.assertEquals(message, expectedMessage);
           System.out.println("Assertion successful for message");

       } catch (AssertionError ae) {
           ae.printStackTrace();
       }

    }
}
