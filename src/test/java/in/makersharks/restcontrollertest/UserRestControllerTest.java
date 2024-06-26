package in.makersharks.restcontrollertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.makersharks.entity.User;
import in.makersharks.restcontroller.UserRestController;
import in.makersharks.service.UserManagementService;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserManagementService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUserRegistration() throws Exception {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("madhavpreeth");
        user.setEmail("m@gmail.com");
        user.setPassword("123234");

        
    
    
     
        when(service.userRegistration(any(User.class))).thenReturn(user);
        
        // Convert user object to JSON
        String userJson = objectMapper.writeValueAsString(user);

        // Create mock HTTP request with JSON body
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);
        
       mockMvc.perform(requestBuilder)
        		.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User registered successfully"));
        
        //assertEquals(201, result.getResponse().getStatus());
        
        
    
}
    
    @Test
    public void userRegistraionWithBadRequest() throws Exception {
    	
    	User user=new User();
    	
    	user.setEmail("");
    	user.setPassword("");
    	
    	when(service.userRegistration(any(User.class))).thenReturn(user);
    	
        String userJson = objectMapper.writeValueAsString(user);
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
        	.contentType(MediaType.APPLICATION_JSON)
        	.content(userJson);
        
        mockMvc.perform(requestBuilder)
        		.andExpect(MockMvcResultMatchers.status().isBadRequest());
        
    	
    }
    
    @Test
    public void getUserByUserNameTest() throws Exception {
    	
    	String username="madhavpreeth";
    	
    	 User user = new User();
         user.setUserId(1);
         user.setUsername("madhavpreeth");
         user.setEmail("m@gmail.com");
         user.setPassword("123234");
    	
    	
    	when(service.fetchUser(anyString())).thenReturn(Optional.of(user));
    	
    	MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/fetch")
    	.param("username", username);
    	
    	mockMvc.perform(requestBuilder)
    		.andExpect(MockMvcResultMatchers.status().isOk())
    		 .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(username))
    	 .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("m@gmail.com"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123234")); // Ensure password is not exposed
}
    
    @Test
    public void userNotFoundTest() throws Exception {
    	
    	String username="abc";
    	
   	 	when(service.fetchUser(anyString())).thenReturn(Optional.empty());
   	 	
   	 	MockHttpServletRequestBuilder param = MockMvcRequestBuilders.get("/api/user/fetch")
   	 		.param("username", username);
   	 	
   	 	mockMvc.perform(param)
   	 		.andExpect(MockMvcResultMatchers.status().isNotFound())
   	 		.andExpect(MockMvcResultMatchers.content().string("User not found"));
    }
    
}
