package in.makersharks.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        User user1 = new User();
        user1.setUserId(1);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setUserId(1); // Same userId as user1
        user2.setUsername("user1");
        user2.setEmail("user1@example.com");
        user2.setPassword("password1");

        // Act & Assert
        assertEquals(user1, user2); // Test equals method
        assertEquals(user1.hashCode(), user2.hashCode()); // Test hashCode method
    }

    @Test
    public void testToString() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("testpassword");

        // Act
        String toStringResult = user.toString();

        // Assert
        assertTrue(toStringResult.contains("User"), "toString should contain class name");
        assertTrue(toStringResult.contains("userId=1"), "toString should contain userId field");
        assertTrue(toStringResult.contains("username=testuser"), "toString should contain username field");
        assertTrue(toStringResult.contains("email=testuser@example.com"), "toString should contain email field");
        assertTrue(toStringResult.contains("password=testpassword"), "toString should contain password field");
    }
}
