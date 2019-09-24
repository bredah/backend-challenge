package api.endpoint;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Test;

import api.BaseTest;
import api.model.Photo;

public class PhotosTest extends BaseTest{

	final String path = "/photos";
	
    @Test
    public void getAllPhotos() {
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
            .param("id", 1500)
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(1));
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
    public void filterByAlbumId() {
        given()
        	.param("id", 1)
        .when()
        	.get(path)
        .then()
            .assertThat()
            .statusCode(200)
            .body("size", equalTo(1));
    }
    
    @Test
    public void filterByAlbumId_notFound() {
        given()
        	.param("id", 501)
        .when()
        	.get(path)
        .then()
            .assertThat()
            .statusCode(200)
            .body("size", equalTo(1));
    }
    
    @Test
    public void filterByAlbumId_invalidValue() {
        given()
        	.param("id", "a")
        .when()
        	.get(path)
        .then()
            .assertThat()
            .statusCode(200)
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByUrl() {
        given()
            .param("url", "https://via.placeholder.com/600/92c952")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(1));
    }
    
    @Test
    public void filterByUrl_notFound() {
        given()
            .param("url", "https://www.google.com/photos/1")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(0));
    }
    
    @Test
    public void filterByThumbnailUrl() {
        given()
        	.param("thumbnailUrl", "https://via.placeholder.com/150/92c952")
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .body("size", equalTo(1));
    }
    
    @Test
    public void filterByThumbnailUrl_notFound() {
        given()
        	.param("thumbnailUrl", "https://www.google.com/photos/1/thumbnail")
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
	    	.body(matchesJsonSchemaInClasspath("schemaPhotos.json"));
	}

	@Test
	public void validateUrlField() {
		List<Photo> photos = when()
	    	.get(path)
	    .then()
	    	.spec(getResponseSpec())
	    	.extract().body().jsonPath().getList(".", Photo.class);
		
		for (Photo photo : photos) {
			assertTrue(
					"Invalid url: " + photo,
    			 	UrlValidator.getInstance().isValid(photo.getUrl()));
		}	
	}

	@Test
    public void validateThumbnailUrlField() {
		List<Photo> photos = when()
        	.get(path)
    	.then()
        	.spec(getResponseSpec())
        	.extract().body().jsonPath().getList(".", Photo.class);
		
		for (Photo photo : photos) {
			assertTrue(
					"Invalid thumbnail url: " + photo,
    			 	UrlValidator.getInstance().isValid(photo.getThumbnailUrl()));
		}
    }
}
