package pl.mbaleczny.instalike.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest extends BaseModelTest {

    @Test
    public void deserialize() throws Exception {
        String json = "{\n" +
                "\"id\": 215,\n" +
                "\"first_name\": \"Mike\",\n" +
                "\"last_name\": \"Tyson\",\n" +
                "\"email\": \"tyson@3amp.com\",\n" +
                "\"username\": \"mikey\",\n" +
                "\"phone_number\": null,\n" +
                "\"role\": \"user\",\n" +
                "\"active\": null\n" +
                "}";

        User user = gson.fromJson(json, User.class);

        Assert.assertNotNull(user);
        Assert.assertEquals(215, user.getId());
        Assert.assertEquals("Mike", user.getFirstName());
        Assert.assertEquals("Tyson", user.getLastName());
        Assert.assertEquals("tyson@3amp.com", user.getEmail());
        Assert.assertEquals("mikey", user.getUsername());
        Assert.assertEquals("user", user.getRole());
        Assert.assertNull(user.getPhoneNumber());
        Assert.assertFalse(user.isActive());
    }
}
