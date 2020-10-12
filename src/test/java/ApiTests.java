
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
    ApiClient apiClient = new ApiClient("https://jsonplaceholder.typicode.com");
//post i comments
    @Test
    public void getMaxValueForUserId() {
        Response posts = apiClient.getPosts();
        List<Integer> userIds = posts.jsonPath().getList("userId");
        int max = userIds.stream().max(Integer::compareTo).get();
        LOGGER.info(String.valueOf(max));
    }

    @Test
    public void getMaxValueForId() {
        Response posts = apiClient.getPostsByUser("10");
        List<Integer> postIds = posts.jsonPath().getList("id");
        int max = postIds.stream().max(Integer::compareTo).get();
        LOGGER.info(String.valueOf(max));
    }

    @Test
    public void addNewCommentForPostId() {
        Response posts = apiClient.getPostsByUser("10");
        List<Integer> postIds = posts.jsonPath().getList("id");
        int max = postIds.stream().max(Integer::compareTo).get();
        LOGGER.info(String.valueOf(max));

        JSONObject json = new JSONObject();
        json.put("postId", max);
        json.put("name", faker.name().fullName());
        json.put("email", faker.name().username() + "@gmail.com");
        json.put("body", faker.chuckNorris().fact());

        Response res = apiClient.addComment(json.toString());
        LOGGER.info(res.jsonPath().getString("$"));
    }
}
