package api.endpoint;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

import api.BaseTest;
import api.model.User;

public class UsersTest extends BaseTest {

	final String path = "/users";
	
    @Test
    public void getAllUsers() {
    	given()
			.spec(getRequestSpec())
        .when()
            .get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", greaterThan(0));
    }

    @Test
    public void filterById() {
        given()
            .param("id", 1)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", greaterThan(0));
    }
    
    @Test
    public void filterById_notFound() {
        given()
            .param("id", 151)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterById_invalidValue() {
        given()
            .param("id", "a")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByName() {
        given()
            .param("name", "Leanne Graham")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", greaterThan(0));
    }
    
    @Test
    public void filterByName_notFound() {
        given()
            .param("name", "Leanne Graham Otaku")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByName_invalidValue() {
        given()
            .param("name", 1)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByUsername() {
        given()
            .param("username", "Bret")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", greaterThan(0));
    }
    
    @Test
    public void filterByUsername_notFound() {
        given()
            .param("username", "Bretanie")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByUsername_invalidValue() {
        given()
            .param("username", 1)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
            .body("size", equalTo(0));
    }
        
    @Test
    public void filterByEmail() {
        given()
        	.param("email","Sincere@april.biz")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
        	.body("size", equalTo(1));
    }
    
    @Test
    public void filterByEmail_notFound() {
        given()
        	.param("email","Sincere@april.bizzy")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
    
    @Test
    public void filterByWebsite() {
        given()
        	.param("website", "hildegard.org")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
    		.spec(getResponseSpec())
        	.body("size", equalTo(1));
    }
    
    @Test
    public void filterByWebsite_notFound() {
        given()
        	.param("website", "hildegard.organize")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
    		.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
    
    @Test
    public void filterByPhone() {
        given()
        	.param("phone", "1-770-736-8031 x56442")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
        	.body("size", equalTo(1));
    }
    
    @Test
    public void filterByPhone_notFound() {
        given()
        	.param("phone", "1-770-736-8031")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
			.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
    
    @Test
    public void validateEmailField_RFC5322() {
    	List<User> users = when()
    		 .get(path)
        .then()
        	.spec(getResponseSpec())
        	.extract().body().jsonPath().getList(".", User.class);
    	
    	for (User user : users) {
    		assertTrue(
    				"Invalid email: " + user,
    			 	EmailValidator.getInstance().isValid(user.getEmail()));
    	}
    }
    
    @Test
    public void validateSchema() {
    	given()
    		.param("id", "1")
    		.spec(getRequestSpec())
		.when()
			.get(path)
		.then()
        	.spec(getResponseSpec())
        	.body(matchesJsonSchemaInClasspath("schemaUsers.json"));
    }
}
