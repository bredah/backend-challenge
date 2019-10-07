package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import api.model.Photo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Photos {
	public static List<Photo> find(Photo photo){
		if(photo == null) { return new ArrayList<Photo>(); }
		return given()
			.spec(requestSpec(photo))
		.when()
			.get(Endpoints.PHOTOS.getPath())
		.then()
			.extract().body().jsonPath().getList(".", Photo.class);
	}
	
	private static RequestSpecification requestSpec(Photo photo) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		if (photo.getId() > 0) {
			builder.addParam("id", photo.getId());
		}
		if (photo.getAlbumId() > 0) {
			builder.addParam("albumId", photo.getAlbumId());
		}
		if (photo.getTitle() != null && !photo.getTitle().isEmpty()) {
			builder.addParam("title", photo.getTitle());
		}
		if (photo.getUrl() != null && !photo.getUrl().isEmpty()) {
			builder.addParam("url", photo.getUrl());
		}
		return builder.build();
	}
}
