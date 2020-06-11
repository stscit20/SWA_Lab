package de.hse.swa.SWA_Lab.resources;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import de.hse.swa.SWA_Lab.model.*;
import de.hse.swa.SWA_Lab.dao.UserDao;
import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.ServicecontractDao;
import de.hse.swa.SWA_Lab.PrepareTests;
import de.hse.swa.SWA_Lab.resources.*;

public class ServicecontractResourceTest {
private static Servicecontract servicecontract=null;
	private static ServicecontractResource servicecontractresource=null;
	
	private static Company company = null;
	private static Swauser swauser = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//PrepareTests.initDatabase();
	//    company = new Company();
	//	company.setCompanyname("TestCompany");
		 
	//	CompanyDao.getInstance().saveCompany(company);
		
		
	//	swauser = new Swauser();
	//	swauser.setUsername("Test Name");
	//	swauser.setPassword("test");
	//	swauser.setCompany(company);
	
	//    UserDao.getInstance().saveSwauser(swauser);
}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		PrepareTests.initDatabase();
	    company = new Company();
		company.setCompanyname("TestCompany");
		CompanyDao.getInstance().saveCompany(company);
		Swauser user = new Swauser();
		user.setUsername("test");
		user.setPassword("123");
		user.setCompany(company);
		UserDao.getInstance().saveSwauser(user);;
		servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(user);
		ServicecontractDao.getInstance().saveServicecontract(servicecontract);
		servicecontractresource =new ServicecontractResource();
	}
	public void tearDown() throws Exception {
	}

	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testGetServicecontract(){
		assertEquals(servicecontractresource.getServicecontract(servicecontract.getIdservicecontract()),servicecontract);
	}
	
	@Test
	public void testGetServicecontracttHtml(){
		try{
			servicecontractresource.getServicecontractHTML(servicecontract.getIdservicecontract());
		}
		catch(RuntimeException e){
			assertTrue(false);
		}
		assertTrue(true);
		
	}
	
	@Test
	public void testDeleteServicecontract(){
		servicecontractresource.deleteServicecontract(servicecontract.getIdservicecontract());
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Get: Servicecontract with " + servicecontract.getIdservicecontract() +  " not found");
		servicecontractresource.getServicecontractHTML(servicecontract.getIdservicecontract());
	}
}
