package de.hse.swa.SWA_Lab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hse.swa.SWA_Lab.model.Company;

public class CompanyDao {

	private static EntityManager em;
	private static CompanyDao singleton;

	
	private CompanyDao() {
		em = DaoManager.getInstance().getEntityManager();
	}
	
	public static CompanyDao getInstance() {
		if (singleton == null) {
			singleton = new CompanyDao();
		}
		return singleton;
	}
	
	public Company getCompany(Integer id) {
		return em.find(Company.class, id);
	}
	
	public List<Company> getCompanies(){
		Query q = em.createQuery("select c from Company c");
		List<Company> companies = q.getResultList();
		return companies;
	}
	
	public void saveCompany(Company company) {
		em.getTransaction().begin();
		em.persist(company); // UNTIL HERE IT FAILS!
		em.getTransaction().commit();
	}
	
	public void deleteCompany(Integer id) {
		Company company = em.find(Company.class, id);
		if (company != null) {
			em.getTransaction().begin();
			em.refresh(CompanyDao.getInstance().getCompany(id));
			em.getTransaction().commit();
		}
	}
}
