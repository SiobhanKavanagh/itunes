package dss.project.dao;

import java.util.Collection;

import dss.project.entities.User;

public interface UserDAO {

	public void addUser(User user);
	
	public User getUser(String username);
	
	public Collection <User> getAllUsers();

	void addUser(Collection<User> users);
}
