package de.hse.swa.SWA_Lab.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.SWA_Lab.PrepareTests;
import de.hse.swa.SWA_Lab.model.Swauser;
import de.hse.swa.SWA_Lab.model.Company;

public class SwauserDaoTest {
	
	 private static Company company = null;
	
	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception{
		 PrepareTests.initDatabase();
		 
		 company = new Company();
		 company.setCompanyname("TestCompany");
		 
		 CompanyDao.getInstance().saveCompany(company);
		 
	 }
	 
	 @AfterClass
	 public static void tearDownAfterClass() throws Exception{
		 
	 }
	 
	 @Before
	 public void setUp() throws Exception{
		 
	 }
	 
	 @After
	 public void tearDown() throws Exception{
		 
	 }
	 
	 
	 // how does it work with foreign keys: here: company_idcompany
	 @Test
	 public void testGetswauser() {
		 UserDao c = UserDao.getInstance();
		 Swauser user = new Swauser();
		 user.setUsername("Test Name");
		 user.setPassword("test");
		 user.setCompany(company);
		 c.saveSwauser(user);
		 Swauser users = c.getSwauser(user.getIduser()); // Get ID of frequently created user.
		 assertNotNull(users);		 
	 }
	 
	 @Test
	 public void testGetswausers() {
		 UserDao c = UserDao.getInstance();
		 Swauser user;
		 for (long i=0; i < 10; ++i) {
			 user = new Swauser();
			 user.setUsername("John Doe sen. "+i);
			 user.setPassword("test" +i);
			 user.setCompany(company);
			
			 c.saveSwauser(user) ;
		 }
		 List<Swauser> users = c.getSwausers();
		 assertTrue(users.size()>=10);
	 }
	 
	 @Test
	 public void testDeleteswausers() {
		 UserDao userDao = UserDao.getInstance();
		 List<Swauser> users = userDao.getSwausers();
		 for (Swauser user: users) {
			 userDao.deleteSwauser(user.getIduser());
		 }
		 users = userDao.getSwausers();
		 assertTrue(users.size()<1);
	 }
	 
}
