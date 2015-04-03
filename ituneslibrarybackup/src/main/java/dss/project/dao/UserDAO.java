package dss.project.dao;

import java.util.Collection;

import dss.project.entities.User;

public interface UserDAO {

	public void addUser(Collection<User> users);
	
	public User getUser(String username);
	
	public Collection <User> getAllUsers();
}
