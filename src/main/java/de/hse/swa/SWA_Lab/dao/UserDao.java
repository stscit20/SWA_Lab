package de.hse.swa.SWA_Lab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.hse.swa.SWA_Lab.model.Swauser;


public class UserDao {
	private static EntityManager em;
	private static UserDao singleton;
	
	private UserDao() {
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static UserDao getInstance() {
		if (singleton == null) {
			singleton = new UserDao();
		}
		return singleton;
	}
	
	public Swauser getSwauser(Integer id) {
		return em.find(Swauser.class, id);
	}
	
	public List<Swauser> getSwausers() {
		Query q = em.createQuery("select c from Swauser c");
		List<Swauser> users = q.getResultList();
		return users;
	}
	
	public void saveSwauser(Swauser user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public void deleteSwauser(Integer id) {
		Swauser cm = em.find(Swauser.class, id);
		if(cm!=null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
