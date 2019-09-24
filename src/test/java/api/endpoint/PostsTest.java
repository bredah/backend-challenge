package api.endpoint;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;

import api.BaseTest;

public class PostsTest extends BaseTest{

	final String path = "/posts";
	
    @Test
    public void getAllPosts() {
        when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", greaterThan(0));
    }

    @Test
    public void filterById() {
        given()
            .param("id", 1)
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(1));
    }
    
    @Test
    public void filterById_notFound() {
        given()
            .param("id", 150)
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterById_invalidValue() {
        given()
            .param("id", "a")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByUserId() {
        given()
            .param("userId", 1)
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", greaterThan(0));
    }
    
    @Test
    public void filterByUserId_notFound() {
        given()
            .param("userId", 150)
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByUserId_invalidValue() {
        given()
            .param("userId", "a")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByTitle() {
        given()
            .param("title", "qui est esse")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(1));
    }
    
    @Test
    public void filterByTitle_notFound() {
        given()
        	.param("title", "qui est esse S")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(0));
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
        	.body(matchesJsonSchemaInClasspath("schemaPosts.json"));
    }
}
