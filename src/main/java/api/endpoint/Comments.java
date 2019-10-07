package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import api.model.Comment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Comments {
	public static List<Comment> find(Comment comment){
		if(comment == null) { return new ArrayList<Comment>(); }
		return given()
			.spec(requestSpec(comment))
		.when()
			.get(Endpoints.COMMENTS.getPath())
		.then()
			.extract().body().jsonPath().getList(".", Comment.class);
	}
	
	private static RequestSpecification requestSpec(Comment comment) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		if(comment.getId() > 0) {
			builder.addParam("id", comment.getId());
		}
		if(comment.getPostId() > 0) {
			builder.addParam("postId", comment.getPostId());
		}
		if(comment.getName() != null && !comment.getName().isEmpty()) {
			builder.addParam("name", comment.getName());
		}
		if(comment.getEmail() != null && !comment.getEmail().isEmpty()) {
			builder.addParam("email", comment.getEmail());
		}
		return builder.build();
	}
}
