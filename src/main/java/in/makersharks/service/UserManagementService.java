package in.makersharks.service;

import java.util.Optional;

import in.makersharks.entity.User;

public interface UserManagementService {

	User userRegistration(User user);
	
	Optional<User>  fetchUser(String username);
}
