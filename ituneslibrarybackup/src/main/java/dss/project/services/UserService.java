package dss.project.services;

import java.util.Collection;
import javax.ejb.Local;
import dss.project.entities.User;

@Local
public interface UserService {

	public Collection <User> getAllUsers();
	
	public void addUser(User user);
	}
