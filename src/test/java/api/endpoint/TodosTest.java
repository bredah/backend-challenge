package api.endpoint;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;

import api.BaseTest;

public class TodosTest extends BaseTest {

	final String path = "/todos";

    @Test
    public void getAllTodos() {
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
   	    	.param("id", 1501)
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
    public void filterByUserId() {
        given()
        	.param("userId", 1)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", greaterThan(1));
    }
	
	@Test
    public void filterByUserId_notFound() {
        given()
        	.param("userId", 151)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
	
	@Test
    public void filterByUserId_invalidValue() {
        given()
        	.param("userId", 151)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", equalTo(0));
    }
	
	@Test
	public void filterByIdAndUserId() {
	    given()
	    	.param("id", 2)
	    	.param("userId", 1)
			.spec(getRequestSpec())
	    .when()
	    	.get(path)
	    .then()
	    	.spec(getResponseSpec())
	    	.body("size", equalTo(1));
	}
	
	@Test
    public void filterByCompleted_IsTrue() {
        given()
        	.param("completed", true)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", greaterThan(1));
    }
	
	@Test
    public void filterByCompleted_IsFalse() {
        given()
        	.param("completed", false)
    		.spec(getRequestSpec())
        .when()
        	.get(path)
        .then()
        	.spec(getResponseSpec())
        	.body("size", greaterThan(1));
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
        	.body(matchesJsonSchemaInClasspath("schemaTodos.json"));
    }
}
