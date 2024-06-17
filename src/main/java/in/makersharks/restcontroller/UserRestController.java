package in.makersharks.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.makersharks.entity.User;
import in.makersharks.service.UserManagementService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserManagementService service;
	

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
		
		service.userRegistration(user);
		
		return new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
		
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<Object> getUser(@RequestParam String username){
		
		Optional<User> fetchUser = service.fetchUser(username);
		
		if(fetchUser.isPresent()) {
			
			 return ResponseEntity.ok(fetchUser.get());
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}
	
	
}
