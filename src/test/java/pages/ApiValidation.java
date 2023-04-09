package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ApiValidation {

    public Response response;

    //////////////////////////// 3 metod birlikte baglatili
    private String updateBody(String name, String gender, String email, String status) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "src/test/resources/data/userDetails.json")));
            body = body.replaceAll("replaceName", name);
            body = body.replaceAll("replaceEmail", email);
            body = body.replaceAll("replaceGender", gender);
            body = body.replaceAll("replaceStatus", status);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return body;
    }

    public Response postMethod(String name, String gender, String email, String status) {
        String requestBody = updateBody(name, gender, email, status);
        Response response = given().headers("Authorization", "Bearer 73558c2f1839b4cbe1bb22a857e2d2ecd756534cba0738abcd37bc58167f5032", "Content-Type", "application/json", "Accept", ContentType.JSON)
                .body(requestBody)
                .post();


        return response;

    }//postmethod/////

    public Response putMethod(String name, String gender, String email, String status) {
        String requestBody = updateBody(name, gender, email, status);
        Response response = given().headers("Authorization", "Bearer 73558c2f1839b4cbe1bb22a857e2d2ecd756534cba0738abcd37bc58167f5032",
                        "Content-Type", "application/json", "Accept", ContentType.JSON)
                .body(requestBody)
                .put();

        return response;
    }//putmethod


    //////////////////////////// 2 metod birlikte baglatili
    private String updatePostBody(String title, String postbody) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "src/test/resources/data/postCreate.json")));
            body = body.replaceAll("replaceTitle", title);
            body = body.replaceAll("replaceBody", postbody);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return body;
    }

    public Response postMethodCreate(String title, String body) {
        String requestBody = updatePostBody(title, body);
        Response response = given().headers("Authorization", "Bearer 73558c2f1839b4cbe1bb22a857e2d2ecd756534cba0738abcd37bc58167f5032",
                        "Content-Type", "application/json", "Accept", ContentType.JSON)
                .body(requestBody)
                .post();

        return response;

    }//postMethodCreate


    //////////////////////////// 2 metod birlikte baglatili
    private String updatePostBody(String comment, String name, String email) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "src/test/resources/data/createComment.json")));
            body = body.replaceAll("replaceBody", comment);
            body = body.replaceAll("replaceName", name);
            body = body.replaceAll("replaceEmail", email);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return body;
    }

    public Response postMethodComment(String comment, String name, String email) {
        String requestBody = updatePostBody(comment, name,email);
        Response response = given().headers("Authorization", "Bearer 73558c2f1839b4cbe1bb22a857e2d2ecd756534cba0738abcd37bc58167f5032",
                        "Content-Type", "application/json", "Accept", ContentType.JSON)
                .body(requestBody)
                .post();

        return response;

    }//postMethodComment


    //////////////////////////// delete metodu
    public Response deleteMethod() {
        Response response = given().headers("Authorization", "Bearer 73558c2f1839b4cbe1bb22a857e2d2ecd756534cba0738abcd37bc58167f5032",
                        "Content-Type", "application/json", "Accept", ContentType.JSON)
                .delete();


        return response;




    }// DELETE method


}//son
