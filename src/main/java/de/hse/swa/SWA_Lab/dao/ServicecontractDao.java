package de.hse.swa.SWA_Lab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import de.hse.swa.SWA_Lab.model.Servicecontract;

public class ServicecontractDao {

	private static EntityManager em;
	private static ServicecontractDao singleton;
	
	private ServicecontractDao() {
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static ServicecontractDao getInstance() {
		if (singleton == null) {
			singleton = new ServicecontractDao();
		}
		return singleton;
	}
	
	public Servicecontract getServicecontract(Integer id) {
		return em.find(Servicecontract.class, id);
	}
	
	public List<Servicecontract> getSerivecontractByCompanyId(Integer id){
		Query q = em.createQuery("select s from servicecontract s where s.company_idcompany = :cid");
		q.setParameter("cid", CompanyDao.getInstance().getCompany(id));
		List<Servicecontract> servicecontracts = q.getResultList();
		return servicecontracts;
	}
	
	public List<Servicecontract> getSerivecontractByUserId(Integer id){
		Query q = em.createQuery("select s from servicecontract s where s.user_iduser = :uid");
		q.setParameter("uid", UserDao.getInstance().getSwauser(id));
		List<Servicecontract> servicecontracts = q.getResultList();
		return servicecontracts;		
	}
	
	public List<Servicecontract> getServicecontracts(){
		Query q = em.createQuery("select s from servicecontract s");
		List<Servicecontract> servicecontracts = q.getResultList();
		return servicecontracts;
	}
	
	public void saveServicecontract(Servicecontract servicecontract) {
		em.getTransaction().begin();
		em.persist(servicecontract);
		em.getTransaction().commit();
	}
	
	public void deleteServicecontract(Integer id) {
		Servicecontract servicecontract = em.find(Servicecontract.class, id);
		if (servicecontract != null) {
			em.getTransaction().begin();
			em.remove(servicecontract);
			em.getTransaction().commit();
		}
	}
	
	public void updateServicecontract(int id) {
		em.getTransaction().begin();
		em.refresh(ServicecontractDao.getInstance().getServicecontract(id));
	}
	
	

}
