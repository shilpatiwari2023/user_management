package api.tests;

import api.base.BaseTest;
import api.config.Endpoints;
import api.payload.UserPayLoad;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCrudTests extends BaseTest {
    String token = "Bearer <your_gorest_token>";
    int user_Id;
    @Test(priority = 1)
    public void createUser(){
        Response response = given().header("Authorization",token)
                .header("Content-Type","application/json")
                .body(UserPayLoad.getUserPayload())
                .post(Endpoints.Create_User);

        response.then().statusCode(201);
        user_Id = response.jsonPath().getInt("id"0);
    }
    @Test(priority = 2,dependsOnMethods = "createUser")
    public void getUser(){
        Response response = given().header("Authorization",token)
                .pathParam("id",user_Id)
                .get(Endpoints.Get_User);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getInt("id"),user_Id);
    }
    @Test(priority = 3,dependsOnMethods = "getUser")
    public void updateUser(){
        String updatedPayload = "{\"name\" : \"ShilpaUpdated\",\"status\" : \"Inactive\"}";
        Response response = given().header("Authorization",token)
                .header("Content-Type","application/json")
                .pathParam("id",user_Id)
                .body(updatedPayload)
                .put(Endpoints.Update_User);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("name"),"ShilpaUpdated");
    }
    @Test(priority = 4,dependsOnMethods = "updateUser")
    public void deleteUser() {
        Response response = given().header("Authorization", token)
                .pathParam("id", user_Id)
                .delete(Endpoints.Delete_User);
        Assert.assertEquals(response.statusCode(), 204);
    }
    @Test(priority = 5)
    public void deleteUserAgain_ShouldFail(){
        Response response = given().header("Authorization", token)
                .pathParam("id", user_Id)
                .delete(Endpoints.Delete_User);
        Assert.assertEquals(response.statusCode(), 404);
    }
}
