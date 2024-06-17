package in.makersharks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.makersharks.entity.User;
import in.makersharks.repository.UserRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserRepository repo;
	@Override
	public User userRegistration(User user) {
		
		return repo.save(user);
		
	}
	
	@Override
	public Optional<User>  fetchUser(String username) {
		
		return  repo.findByUsername(username);
	}
		
}
