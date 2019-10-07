package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import api.model.Album;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Albums {

	public static List<Album> find(Album album){
		if(album == null) { return new ArrayList<Album>(); }
		return given()
			.spec(requestSpec(album))
		.when()
			.get(Endpoints.ALBUMS.getPath())
		.then()
			.extract().body().jsonPath().getList(".", Album.class);
	}
	
	private static RequestSpecification requestSpec(Album album) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		if(album.getUserId() > 0) {
			builder.addParam("userId", album.getUserId());
		}
		if(album.getId() > 0) {
			builder.addParam("id", album.getId());
		}
		if(album.getTitle() != null && !album.getTitle().isEmpty()) {
			builder.addParam("title", album.getTitle());
		}
		return builder.build();
	}
}
