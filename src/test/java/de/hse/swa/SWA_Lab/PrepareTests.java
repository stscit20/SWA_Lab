package de.hse.swa.SWA_Lab;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import de.hse.swa.SWA_Lab.dao.DaoManager;

public class PrepareTests {
	@Test
	public void clearDB() {
		initDatabase();
	}
	
	public static void initDatabase() {
		DaoManager dm = DaoManager.getInstance();
		EntityManager em = dm.getEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et = em.getTransaction();
		et.begin();
		

		try {
			em.createNativeQuery("DELETE FROM license").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

		
		try {
			em.createNativeQuery("DELETE FROM servicecontract").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

		
		try {
			em.createNativeQuery("DELETE FROM swauser").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			em.createNativeQuery("DELETE FROM company").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		et.commit();
	
		

		et = em.getTransaction();
		et.begin();
		
		// Begin SQL Queries
/*		
		// Insert into Company
		em.createNativeQuery("INSERT INTO company (idcompany, companyname, department, address) VALUES (1, 'Riot Games', 'USA', 'saltystreet 5')").executeUpdate();
		
		// USER
		em.createNativeQuery("INSERT INTO swauser (iduser, username, email, password, first_name, last_name, is_admin, company_idcompany) Values "
				+ "(1, 'xXBananaExpress', 'BananaExpress@gmail.com', 'test123', 'Hans', 'Peter', 1, 1)").executeUpdate();
		
		// INSERT into servicecontract
		em.createNativeQuery("INSERT INTO servicecontract (idservicecontract, startdate, enddate, company_idcompany, user_iduser) VALUES"
				+ " (1, '2021-09-01 10:10:10', '2022-09-01 10:10:10', 1, 1)" ).executeUpdate();		
		
		// Insert into License
		em.createNativeQuery("INSERT INTO license (idlicense, expirationdate, licensekey, IP1, IP2, IP3, IP4, licensecount, servicecontract_idservicecontract)"
				+ "VALUES (1, '2020-09-01 10:10:10', 'licensekey', '127.0.0.1', '10.20.131.103', '10.20.131.198', '192.168.178.24', 5, 1)").executeUpdate();
		
*/
		
		et.commit();
		
	}
}
