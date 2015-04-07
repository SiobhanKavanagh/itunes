package dss.project.services;

import java.util.Collection;
import javax.ejb.Local;
import dss.project.entities.User;


public interface UserService {

	public Collection <User> getAllUsers();
	
	public void insertUser(User user);
	
	public User getUser(String userId);
	}
