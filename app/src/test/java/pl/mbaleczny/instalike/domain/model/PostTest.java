package pl.mbaleczny.instalike.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class PostTest extends BaseModelTest {

    @Test
    public void deserialize() throws Exception {
        String json = "{\n" +
                "\"id\": 839,\n" +
                "\"image_url\": \"dummy_url\",\n" +
                "\"comment\": null,\n" +
                "\"user_id\": 215,\n" +
                "\"event_id\": 3,\n" +
                "\"active\": null,\n" +
                "\"created_at\": \"2016-01-16 12:00:00\",\n" +
                "\"updated_at\": \"2016-01-16 12:00:00\",\n" +
                "\"userLiked\": true,\n" +
                "\"user\": {\n" +
                "\"id\": 215,\n" +
                "\"first_name\": \"Mike\",\n" +
                "\"last_name\": \"Tyson\",\n" +
                "\"email\": \"mikey@3amp.com\",\n" +
                "\"username\": \"mTyson\",\n" +
                "\"phone_number\": null,\n" +
                "\"role\": \"user\",\n" +
                "\"active\": null\n" +
                "},\n" +
                "\"likes_count\": [" +
                "{\n" +
                "\"image_id\": 839,\n" +
                "\"likes\": 3\n" +
                "}" +
                "],\n" +
                "\"comments_count\": []\n" +
                "}";

        Post post = gson.fromJson(json, Post.class);

        Assert.assertNotNull(post);
        Assert.assertEquals(215, post.getUserId());
        Assert.assertEquals(3, post.getEventId());
        Assert.assertEquals("dummy_url", post.getImageUrl());
        Assert.assertNotNull(post.getLikes());
        Assert.assertEquals(3, post.getLikesCount());
        Assert.assertNotNull(post.getComments());
        Assert.assertEquals(0, post.getCommentsCount());
        Assert.assertNotNull(post.getCreatedAt());
        Assert.assertTrue(post.isUserLiked());

        Calendar c = Calendar.getInstance();
        c.setTime(post.getCreatedAt());
        Assert.assertEquals(2016, c.get(Calendar.YEAR));
        Assert.assertEquals(0, c.get(Calendar.MONTH));
        Assert.assertEquals(16, c.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(12, c.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(0, c.get(Calendar.MINUTE));
        Assert.assertEquals(0, c.get(Calendar.SECOND));

        Assert.assertEquals(post.getCreatedAt(), post.getUpdatedAt());
    }
}
