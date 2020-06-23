package de.hse.swa.SWA_Lab.dao;

import java.util.List;

import javax.persistence.EntityManager;
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
	
	public List<Swauser> getUsersByCompanyId(Integer id){
		Query q = em.createQuery("select u from Swauser u where u.company_idcompany = :cid");
		q.setParameter("cid", CompanyDao.getInstance().getCompany(id));
		List<Swauser> users = q.getResultList();
		return users;
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

	public List<Swauser> getUsersByUsername(String name){
		Query q = em.createQuery("select u from Swauser u where u.username = :uname");
		q.setParameter("uname", name);
		List<Swauser> users = q.getResultList();
		return users;
	}

	public Swauser getLoggedUser(String name, String password){
		Query q = em.createQuery("select u from Swauser u where u.username = :uname and u.password = :password");
		q.setParameter("uname", name);
		q.setParameter("password", password);
		Swauser user = null;
		if(q.getResultList().size()>0) {
			user = (Swauser) q.getResultList().get(0);
		}
				
		return user;
	}
}
