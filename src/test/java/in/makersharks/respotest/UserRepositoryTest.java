package in.makersharks.respotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import in.makersharks.entity.User;
import in.makersharks.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

	@Mock
	private UserRepository repo;
	
	
	@Test
	public void getUserByuserName() {
		
		String username="madhavpreeth";
		User user=new User();
		user.setUserId(1);
        user.setUsername("madhavpreeth");
        user.setEmail("m@gmail.com");
        user.setPassword("123234");
        
        when(repo.findByUsername(username)).thenReturn(Optional.of(user));
        
        Optional<User> result = repo.findByUsername(username);
        
        //assertEquals(user, result.orElse(null));
        
        assertTrue(result.isPresent());
		
	}
	
	@Test
	public void userNotFoundTest() {
		
		String username="macbvsn";
		
		when(repo.findByUsername(anyString())).thenReturn(Optional.empty());
		
		Optional<User> findByUsername = repo.findByUsername(username);
		
		assertFalse(findByUsername.isPresent());
	}
	
	
}
