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
			em.createNativeQuery("DELETE FROM company").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			em.createNamedQuery("Delete FROM license").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			em.createNamedQuery("DELETE FROM servicecontract").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			em.createNamedQuery("DELETE FROM swauser").executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		et.commit();
		et.begin();
		et.commit();
		
	}
}
