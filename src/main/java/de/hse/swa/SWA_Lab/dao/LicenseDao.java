package de.hse.swa.SWA_Lab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import de.hse.swa.SWA_Lab.model.License;

public class LicenseDao {
	
	private static EntityManager em;
	private static LicenseDao singleton;
	
	private LicenseDao() {
		DaoManager dm = DaoManager.getInstance();
		em = dm.getEntityManager();
		
	}
	
	public static LicenseDao getInstance() {
		if(singleton == null) {
			singleton = new LicenseDao();
		}
		return singleton;
	}
	
	public License getLicense(Integer id) {
		return em.find(License.class, id);
	}
	
	public List<License> getLicensesById(Integer id){//servicecontract_idservicecontract
		
		List<License> licenses = em.createQuery("SELECT l FROM License l where l.servicecontract = :sid")
				.setParameter("sid", ServicecontractDao.getInstance().getServicecontract(id))
				.getResultList(); 
	//	q.setParameter("sid", ServicecontractDao.getInstance().getServicecontract(id));
		//List<License> licenses = q.getResultList();
		return licenses;
	}
	/*
	 * public List findWithName(String name) {
		return em.createQuery(
		    "SELECT c FROM Customer c WHERE c.name LIKE :custName")
		    .setParameter("custName", name)
		    .setMaxResults(10)
		    .getResultList();
		}
}
	 * */
	
	public List<License> getLicenses(){
		@SuppressWarnings("unchecked")
		List<License> licenses =em.createNativeQuery("SELECT * FROM license").getResultList();
		return licenses;
	}
	
	public void saveLicense(License license) {
		em.getTransaction().begin();
		em.persist(license);
		em.getTransaction().commit();
	}
	
	public void deleteLicense(Integer id) {
		License license = em.find(License.class, id);
		if(license != null) {
			em.getTransaction().begin();
			em.remove(license);
			em.getTransaction().commit();
		}
	}
	
	public void updateLicense(int id) {
		em.getTransaction().begin();
		em.refresh(LicenseDao.getInstance().getLicense(id));
		em.getTransaction().commit();
	}
	
}
