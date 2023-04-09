package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.ApiValidation;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DataTableSteps extends ApiValidation {





    @Then("validate the user details")
    public void validateTheUserDetails(DataTable dataTable) {
        List<String> fields = dataTable.asList();


//        Response response;
//            String requestBody = fields.
//            Response response=given().headers("Authorization", "Bearer 73558c2f1839b4cbe1bb22a857e2d2ecd756534cba0738abcd37bc58167f5032",
//                            "Content-Type", "application/json", "Accept", ContentType.JSON)
//                    .body(requestBody)
//                    .post();
//
//
//
//            return response;




        int id = response.jsonPath().getInt("data.id");
        System.out.println(id);
        System.out.println(fields.get(0));
        Assert.assertTrue("id is empty", id != 0);

        String actualName = response.jsonPath().getString("data.name");
        String expactedName= fields.get(1);
        System.out.println(actualName);
        Assert.assertEquals(expactedName, actualName);
        System.out.println("Assertion success :" + actualName);

        String actualGender = response.jsonPath().getString("data.gender");
        System.out.println(actualGender);
        String expactedgender= fields.get(2);
        Assert.assertEquals(expactedgender, actualGender);
        System.out.println("Assertion success :" + actualGender);

        String actualEmail = response.jsonPath().getString("data.email");
        System.out.println(actualEmail);
        String expactedemail= fields.get(3);
        Assert.assertEquals(expactedemail, actualEmail);
        System.out.println("Assertion success :" + actualEmail);


        String actualStatus = response.jsonPath().getString("data.status");
        System.out.println(actualStatus);
        String expactedstatus= fields.get(4);
        Assert.assertEquals(expactedstatus, actualStatus);
        System.out.println("Assertion success :" + actualStatus);


    }
}
