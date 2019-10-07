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
import api.model.Comment;

public class CommentsTest extends BaseTest {

	final String path = Endpoints.COMMENTS.getPath();	
	
    @Test
    public void getAllComments() {
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
        	.body("size", equalTo(1));
    }
    
    @Test
    public void filterById_notFound() {
        given()
        	.param("id", 1500)
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
    public void filterByEmail() {
        given()
        	.param("email", "Eliseo@gardner.biz")
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
        	.param("email", "Eliseo@gardner.biz.xs")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
    
    @Test
    public void filterByEmail_invalidValue() {
        given()
        	.param("email", "www.google.com")
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
    
    @Test
    public void validateEmailField_RFC5322() {
    	List<Comment> comments = when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.extract().body().jsonPath().getList(".", Comment.class);
    	
    	for (Comment comment : comments) {
    		assertTrue(
    				"Invalid email: " + comment,
    			 	EmailValidator.getInstance().isValid(comment.getEmail()));
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
        	.body(matchesJsonSchemaInClasspath("schemaComments.json"));
    }
}
