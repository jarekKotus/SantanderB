package utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class ApiClient {

    public static Response doGetRequest(String endpoint, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().statusCode(statusCode).contentType(ContentType.JSON).extract().response();
    }

    public static Response doPostRequest(String endpoint, String stringBody, int statusCode) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().body(stringBody).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().post(endpoint).
                        then().statusCode(statusCode).contentType(ContentType.JSON).extract().response();
    }

}
