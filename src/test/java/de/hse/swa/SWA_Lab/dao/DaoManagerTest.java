package de.hse.swa.SWA_Lab.dao;

import javax.persistence.EntityManager;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DaoManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		DaoManager dm = DaoManager.getInstance();
		assertNotNull(dm);
	}

	@Test
	public void testGetEntityManager() {
		EntityManager em = DaoManager.getInstance().getEntityManager();
		assertNotNull(em);
	}
}
