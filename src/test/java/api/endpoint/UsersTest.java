package api.endpoint;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Assert;
import org.junit.Test;

import api.BaseTest;
import api.endpoints.Albums;
import api.endpoints.Comments;
import api.endpoints.Endpoints;
import api.endpoints.Photos;
import api.endpoints.Posts;
import api.endpoints.Users;
import api.model.Album;
import api.model.Comment;
import api.model.Photo;
import api.model.Post;
import api.model.User;

public class UsersTest extends BaseTest {

	final String path = Endpoints.USERS.getPath();
	
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
    
    @Test
    public void checkUserAlbums() {
    	// Find the user
    	User user = new User.Builder()
    		.setName("Leanne Graham")
    		.build();
    	List<User> users = Users.find(user);
    	Assert.assertEquals(users.size(), 1);
    	// Find all album(s) from the user
    	Album album = new Album.Builder()
    			.setUserId(users.get(0).getId())
    			.build();
    	List<Album> albums = Albums.find(album);
    	Assert.assertTrue(albums.size() > 0);    	
    }
    
    @Test
    public void checkUserPhotosFromAlbums() {
    	// Find the user
    	User user = new User.Builder()
    		.setName("Leanne Graham")
    		.build();
    	List<User> users = Users.find(user);
    	Assert.assertEquals(users.size(), 1);
    	// Find all album(s) from the user
    	Album albumBuilder = new Album.Builder()
    			.setUserId(users.get(0).getId())
    			.build();
    	List<Album> albums = Albums.find(albumBuilder);
    	Assert.assertTrue(albums.size() > 0);
    	// Find all photo(s) from the album(s)
    	for (Album album : albums) {
    		Photo photoBuilder = new Photo.Builder()
    				.setAlbumId(album.getId())
    				.build();
			List<Photo> photos = Photos.find(photoBuilder);
			Assert.assertTrue(photos.size() > 0);
		}
    }
    
    @Test
    public void checkUserPosts() {
    	// Find the user
    	User user = new User.Builder()
    		.setName("Leanne Graham")
    		.build();
    	List<User> users = Users.find(user);
    	Assert.assertEquals(users.size(), 1);
    	// Find all post(s) from the user
    	Post post = new Post.Builder()
    			.setUserId(users.get(0).getId())
    			.build();
    	List<Post> posts = Posts.find(post);
    	Assert.assertTrue(posts.size() > 0);	
    }
        
}