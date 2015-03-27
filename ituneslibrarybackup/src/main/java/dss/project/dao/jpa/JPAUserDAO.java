package dss.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.UserDAO;
import dss.project.entities.User;


public class JPAUserDAO implements UserDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public User getUser(String username) {
		List<User> resultList = em.createNamedQuery("findUserByUsername").setParameter("username", username).getResultList();
		if(resultList.size() == 0)
			return null;
		return resultList.get(0);
	}

	@Override
	public Collection<User> getAllUsers() {
		return em.createNamedQuery("findAllUsers").getResultList();
	}


}
