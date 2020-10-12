
import com.github.javafaker.Faker;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import utils.ApiClient;

import java.util.List;
import java.util.logging.Logger;


public class ApiTests {

    private final static Logger LOGGER = Logger.getLogger(ApiTests.class.getName());
    Faker faker = new Faker();

    @Test
    public void getMaxValueForUserId() {
        List<Integer> jsonResponse = ApiClient.doGetRequest("https://jsonplaceholder.typicode.com/posts/", 200).jsonPath().getList("userId");
        int max = jsonResponse.stream().max(Integer::compareTo).get();
        LOGGER.info(String.valueOf(max));
    }

    @Test
    public void getMaxValueForId() {
        List<Integer> jsonResponse = ApiClient.doGetRequest("https://jsonplaceholder.typicode.com/posts?userId=10", 200).jsonPath().getList("id");
        int max = jsonResponse.stream().max(Integer::compareTo).get();
        LOGGER.info(String.valueOf(max));
    }

    @Test
    public void addNewCommentForPostId() {
        List<Integer> jsonResponse = ApiClient.doGetRequest("https://jsonplaceholder.typicode.com/posts?userId=10", 200).jsonPath().getList("id");
        int max = jsonResponse.stream().max(Integer::compareTo).get();
        LOGGER.info(String.valueOf(max));

        JSONObject json = new JSONObject();
        json.put("postId", max);
        json.put("name", faker.name().fullName());
        json.put("email", faker.name().username() + "@gmail.com");
        json.put("body", faker.chuckNorris().fact());

        Response res = ApiClient.doPostRequest("https://jsonplaceholder.typicode.com/comments",
                json.toString(), 201);
        LOGGER.info(res.jsonPath().getString("$"));
    }
}
