package dss.project.dao;

import dss.project.entities.User;

public interface UserDAO {

	public void addUser(User user);
	
	public User getUser(String username);
}
