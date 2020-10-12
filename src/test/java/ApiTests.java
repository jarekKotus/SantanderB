
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;
import com.jayway.restassured.response.Response;
import org.junit.Test;
import utils.ApiClient;
import utils.Comments;
import utils.Posts;
import utils.GetValueMethods;


import java.util.List;
import java.util.logging.Logger;


public class ApiTests {

    private final static Logger LOGGER = Logger.getLogger(ApiTests.class.getName());
    Faker faker = new Faker();
    ApiClient apiClient = new ApiClient("https://jsonplaceholder.typicode.com");

    @Test
    public void getMaxValueForUserId() {
        List<Posts> posts = apiClient.getPosts();
        int max = GetValueMethods.getMaxValueForUserId(posts);
        LOGGER.info(String.valueOf(max));
    }

    @Test
    public void getMaxValueForId() {
        List <Posts> posts = apiClient.getPostsByUser("10");
        int max = GetValueMethods.getMaxValueForId(posts);
        LOGGER.info(String.valueOf(max));
    }

    @Test
    public void addNewCommentForPostId() throws JsonProcessingException {
        List <Posts> posts = apiClient.getPostsByUser("10");
        int max = GetValueMethods.getMaxValueForId(posts);
        LOGGER.info(String.valueOf(max));

        Comments comment = new Comments();
        comment.postId = max;
        comment.name = faker.name().fullName();
        comment.email = faker.name().username() + "@gmail.com";
        comment.body = faker.chuckNorris().fact();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Response res = apiClient.addComment(ow.writeValueAsString(comment));
        LOGGER.info(res.jsonPath().get().toString());
    }
}
