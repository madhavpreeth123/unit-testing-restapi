package in.makersharks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import in.makersharks.entity.User;
import in.makersharks.restcontroller.UserRestController;
import in.makersharks.service.UserManagementService;

@WebMvcTest(UserRestController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserManagementService service;
	
	@InjectMocks
    private UserRestController controller;

	
	@Test
	public void testRegistraion() throws Exception {
		
		User user=new User();
		
		user.setUsername("madhav");
		user.setEmail("madhav@gmail.com");
		user.setPassword("madhav@123");
		
		when(service.userRegistration(any(User.class))).thenReturn(user);
		
		 mockMvc.perform(post("/api/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \"madhav\", \"email\": \"madhav@gmail.com\", \"password\": \"madhav@123\"}"))
        .andExpect(status().isCreated());
		
	}
	
}
