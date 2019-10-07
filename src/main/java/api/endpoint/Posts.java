package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import api.model.Post;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Posts {
	
	public static List<Post> find(Post post){
		if(post == null) { return new ArrayList<Post>(); }
		return given()
			.spec(requestSpec(post))
		.when()
			.get(Endpoints.POSTS.getPath())
		.then()
			.extract().body().jsonPath().getList(".", Post.class);
	}
	
	private static RequestSpecification requestSpec(Post post) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		if(post.getUserId() > 0) {
			builder.addParam("userId", post.getUserId());
		}
		if(post.getId() > 0) {
			builder.addParam("id", post.getId());
		}
		if(post.getTitle() != null && !post.getTitle().isEmpty()) {
			builder.addParam("title", post.getTitle());
		}
		return builder.build();
	}
}
