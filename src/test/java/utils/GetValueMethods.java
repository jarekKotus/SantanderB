package utils;

import java.util.Comparator;
import java.util.List;

public class GetValueMethods {

    public static int getMaxValueForUserId(List<Posts> posts) {
        return posts.stream().max(Comparator.comparingInt(v -> v.userId)).get().userId;
    }

    public static int getMaxValueForId(List<Posts> posts) {
        return posts.stream().max(Comparator.comparingInt(v -> v.id)).get().id;
    }
}
