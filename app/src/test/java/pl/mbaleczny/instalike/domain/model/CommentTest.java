package pl.mbaleczny.instalike.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class CommentTest extends BaseModelTest {

    @Test
    public void deserialize() throws Exception {
        String json = "{\n" +
                "\"id\": 39,\n" +
                "\"comment\": \"Looking good\",\n" +
                "\"image_id\": 838,\n" +
                "\"user_id\": 3,\n" +
                "\"active\": null,\n" +
                "\"user\": {\n" +
                "\"id\": 3,\n" +
                "\"first_name\": \"Ross\",\n" +
                "\"last_name\": \"Feehan\",\n" +
                "\"email\": \"ross@apppli.com\",\n" +
                "\"username\": \"rostaboss\",\n" +
                "\"phone_number\": null,\n" +
                "\"role\": \"admin\",\n" +
                "\"active\": null\n" +
                "}\n" +
                "}";

        Comment comment = gson.fromJson(json, Comment.class);

        Assert.assertNotNull(comment);
        Assert.assertEquals(39, comment.getId());
        Assert.assertEquals(838, comment.getImageId());
        Assert.assertEquals(3, comment.getUserId());
        Assert.assertEquals("Looking good", comment.getComment());
        Assert.assertFalse(comment.isActive());

        User user = comment.getUser();
        Assert.assertNotNull(user);
        Assert.assertEquals("Ross", user.getFirstName());
        Assert.assertEquals("Feehan", user.getLastName());
        Assert.assertEquals("ross@apppli.com", user.getEmail());
        Assert.assertEquals("rostaboss", user.getUsername());
        Assert.assertNull(user.getPhoneNumber());
        Assert.assertEquals("admin", user.getRole());
        Assert.assertFalse(user.isActive());
    }
}
