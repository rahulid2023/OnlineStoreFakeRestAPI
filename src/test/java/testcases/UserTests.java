package testcases;

import pojo.Users;
import routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

//import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.List;
import java.util.Random;
public class UserTests extends BaseClass {
   
	private Random random1=new Random();
	 int userid;
	//View all the carts 
	@Test
	  public void testGetAllUsers() {
		  
		  given()
		   .contentType(ContentType.JSON)
		  .when()
		    .get(Routes.GET_All_USER)
		   .then()
		     .statusCode(200)
        	 .contentType(ContentType.JSON)
             .body("size()", greaterThan(0))
		     .body("x[].address.geolocation", everyItem(notNullValue()))
		     .log().body();
	 }
	    
	
	@Test
	public void testGetUserById() {
	 
		int userId = configReader.getIntProperty("userId");
	 given()
	    .pathParam("id", userId )
	  .when()
	    .get(Routes.GET_USER__BY_ID)
	   .then()
	     .statusCode(200)
	     .log().body()
   	     .contentType(ContentType.JSON)
        // .body("size()", greaterThan(0))
	   .body("address.geolocation.lat", notNullValue())
	   .body("address.geolocation.long", notNullValue());
	 
 }
	
    @Test
	public void testGetUserByLimit() {
	   int limit1= random1.nextInt(4);
	     System.out.println("limit value =>" + limit1);
		//int userId = configReader.getIntProperty("userId");
	 given()
	    .pathParam("limit", limit1)
	  .when()
	    .get(Routes.GET_USERS_WITH_LIMIT)
	   .then()
	     .statusCode(200)
	     .log().body()
   	     .contentType(ContentType.JSON)
         .body("size()", equalTo(limit1))
	     .body("address.geolocation.lat", notNullValue())
	     .body("address.geolocation.long", notNullValue());

	 
 }
	  // Sorted to desc order 
	@Test
	public void testGetUserDescendingSorted() {
    
	Response response =	 given()
	    .pathParam("order", "desc" )
	  .when()
	    .get(Routes.GET_USERS_SORTED)
	   .then()
	     .statusCode(200)
	     .log().body()
   	     .contentType(ContentType.JSON)
         .body("size()", greaterThan(0))
         .extract().response();
	 
        List<Integer> userids = response.jsonPath().getList("id",Integer.class);
	    
        assertThat(isSortedDesceding(userids), is(true));
  }



// Sorted in Ascending order 
   @Test
public void testGetUserAscendingSorted() {

	Response response =	 given()
	   .pathParam("order", "asc" )
	.when()
	   .get(Routes.GET_USERS_SORTED)
	 .then()
	   .statusCode(200)
	   .log().body()
	   .contentType(ContentType.JSON)
	   .body("size()", greaterThan(0))
	   .extract().response();
	  
	   List <Integer> userids= response.jsonPath().getList("id", Integer.class);
	   assertThat(isSortedAsceding(userids), is(true));
}

//Sorted in Ascending order 
@Test
public void testAddNewUser() {

	 Users user = Payload.userPayload();
   userid =	 given()
	   .contentType(ContentType.JSON)
	.when()
	    .body(user)
	   .post(Routes.CREATE_USER)
	 .then()
	    .statusCode(201)
	    .log().body()
	    .contentType(ContentType.JSON)
	    .body("size()", greaterThan(0))
	    .extract().jsonPath().getInt("id");
        
	    assertThat(userid, is(notNullValue()));
	    assertThat("x[].address.geolocation",is(notNullValue()));

}
 @Test
 public void testUpdateUser() {

	 Users updateuser = Payload.userPayload();
	 given()
	   .contentType(ContentType.JSON)
	   .pathParam("id", userid)
	.when()
	    .body(updateuser)
	   .put(Routes.UPDATE_USER)
	 .then()
	    .statusCode(200)
	    .log().body()
	    .contentType(ContentType.JSON)
	    .body("userName", equalTo(updateuser.getUserName()))
	  //.body("id", notNullValue())
	  //.body("status", equalTo("success"))
	    .body("email", containsString("@"));
	 
	//   .body("size()", greaterThan(0))
	//   .extract().jsonPath().getInt("id");
       
	//   assertThat(userid, is(id));
	//   assertThat("x[].address.geolocation",is(notNullValue()));

}
 
 @Test
 public void testDeleteUser() {

	 given()
	   .pathParam("id", userid)
	.when()
	   .put(Routes.DELETE_USER)
	 .then()
	    .statusCode(200)
	    .log().body()
	    .contentType(ContentType.JSON);
	  //  .body("status", equalTo("success"));

  }
}


