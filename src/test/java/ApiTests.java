import com.google.gson.Gson;
import com.google.gson.JsonObject;
import groovy.json.JsonBuilder;
import org.json.JSONObject;
import org.junit.Test;
import utils.ResponseClass;

import java.util.List;
import java.util.Random;


public class ApiTests {

    ResponseClass response;


    @Test
    public void getMaxValueForUserId() {
        List<Integer> jsonResponse = ResponseClass.doGetRequest("https://jsonplaceholder.typicode.com/posts/",200).jsonPath().getList("userId");
          int max=  jsonResponse.stream().max(Integer::compareTo).get();
        System.out.println(max);
    }

    @Test
    public void getMaxValueForId() {
        List<Integer> jsonResponse = ResponseClass.doGetRequest("https://jsonplaceholder.typicode.com/posts?userId=10", 200).jsonPath().getList("id");
        System.out.println(jsonResponse);
       int max = jsonResponse.stream().max(Integer::compareTo).get();
    }

    @Test
    public void post() {
// tworzenie json///////////////
        JSONObject j = new JSONObject();
        j.put("postId",10);
////////////////

        System.out.println( ResponseClass.doPostRequest("https://jsonplaceholder.typicode.com/comments",
              j.toString(),201).jsonPath().get().toString());
    }
}
