package in.makersharks.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import in.makersharks.entity.User;
import in.makersharks.repository.UserRepository;
import in.makersharks.service.UserManagementServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
    private UserRepository userRepository; // Mocking the UserRepository

    @InjectMocks
    private UserManagementServiceImpl userService; // Injecting the service implementation to be tested

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    
    @Test
    public void testUserRegistration() {
        // Arrange
        User user = new User();
        user.setUserId(1); // This would typically be autogenerated by the repository
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User savedUser = userService.userRegistration(user);
        
        // Assert
        assertNotNull(savedUser);
        assertEquals(user.getUserId(), savedUser.getUserId());
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());

        verify(userRepository, times(1)).save(any(User.class)); // Verify that userRepository.save was called exactly once
        
    }
    
    @Test
    public void testUserRegistration_DataIntegrityViolationException() {
        // Arrange
        User user = new User();
        user.setUsername("existinguser"); // Simulating an existing username that might cause a DataIntegrityViolationException

        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class); // Mocking repository to throw DataIntegrityViolationException

        // Act & Assert
        assertThrows(DataIntegrityViolationException.class, () -> userService.userRegistration(user));
    }
    
    @Test
    public void fecthUserTest() {
    	
    	String username="testuser";
    	
    	User user = new User();
        user.setUserId(1); // This would typically be autogenerated by the repository
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        
        Optional<User> fetchUser = userService.fetchUser(username);
        
        assertTrue(fetchUser.isPresent());
    }
    
    @Test
    public void fecthUserNotFoundTest() {
    	
    	String username="abc";
    	
    	User user = new User();
        user.setUserId(1); // This would typically be autogenerated by the repository
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        
        Optional<User> fetchUser = userService.fetchUser(username);
        
        assertFalse(fetchUser.isPresent());
    }
}
