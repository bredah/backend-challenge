package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import api.model.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Users {

	public static List<User> find(User user){
		if(user == null) { return new ArrayList<User>(); }
		return given()
			.spec(requestSpec(user))
		.when()
			.get(Endpoints.USERS.getPath())
		.then()
			.extract().body().jsonPath().getList(".", User.class);
	}
	
	private static RequestSpecification requestSpec(User user) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		if(user.getId() > 0) {
			builder.addParam("id", user.getId());
		}
		if(user.getName() != null && !user.getName().isEmpty()) {
			builder.addParam("name", user.getName());
		}
		if(user.getUsername() != null && !user.getUsername().isEmpty()) {
			builder.addParam("username", user.getUsername());
		}
		if(user.getEmail() != null && !user.getEmail() .isEmpty()) {
			builder.addParam("email", user.getEmail());
		}
		return builder.build();
	}
}
