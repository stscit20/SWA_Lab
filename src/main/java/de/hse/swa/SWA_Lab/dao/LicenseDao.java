package de.hse.swa.SWA_Lab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.License;

public class LicenseDao {
	private static EntityManager em;
	private static LicenseDao singleton;
	
	private LicenseDao() {
		em = DaoManager.getInstance().getEntityManager();
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
	
	public List<License> getLicensesById(Integer id){
		Query q = em.createQuery("select l from license l where l.servicecontract_idservicecontract = :sid");
		q.setParameter("sid", ServicecontractDao.getInstance().getServicecontract(id));
		List<License> licenses = q.getResultList();
		return licenses;
	}
	
	public List<License> getLicenses(){
		Query q = em.createQuery("select l from license l");
		List<License> licenses = q.getResultList();
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
