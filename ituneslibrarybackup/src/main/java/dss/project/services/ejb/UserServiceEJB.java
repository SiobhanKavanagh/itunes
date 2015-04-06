package dss.project.services.ejb;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.UserDAO;
import dss.project.entities.User;
import dss.project.services.UserService;


public class UserServiceEJB implements UserService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserDAO dao;

	@Override
	public Collection<User> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public void addUser(User user) {
		dao.insertUser(user);
	}


}
