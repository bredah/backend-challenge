package api;

import org.apache.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
	
	private RequestSpecification requestSpec;
	private ResponseSpecification responseSpec;
	
	
    public BaseTest() {
        RestAssured.baseURI = getURL();
        RestAssured.defaultParser = Parser.JSON;
        
        setRequestSpec();
        setResponseSpec();
    }


	public RequestSpecification getRequestSpec() {
		return requestSpec;
	}


	public void setRequestSpec() {
		requestSpec = new RequestSpecBuilder()
    			.setContentType("application/json")
    			.build();
	}


	public ResponseSpecification getResponseSpec() {
		return responseSpec;
	}


	public void setResponseSpec() {
		responseSpec = new ResponseSpecBuilder()
    			.expectStatusCode(HttpStatus.SC_OK)
    			.build();
	}
	
	private String getURL() {
		String baseURL = System.getProperty("baseURL");
		if(baseURL !=null) {
			return baseURL;
		}else {
			return "https://jsonplaceholder.typicode.com";
		}
	}
    
}
