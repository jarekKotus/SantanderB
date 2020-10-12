package utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class ApiClient {

    String baseURL;

    public ApiClient(String baseURL) {
        this.baseURL = baseURL;
    }

    public Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(this.baseURL + endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    public Response doPostRequest(String endpoint, String stringBody) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().body(stringBody).headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().post(this.baseURL + endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    public Response getPosts() {
        return doGetRequest("/posts");
    }

    public Response getPostsByUser(String userId) {
        return doGetRequest("/posts?userId=" + userId);
    }

    public Response addComment(String comment) {
        return doPostRequest("/comments", comment);
    }
}
