package de.hse.swa.SWA_Lab.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.SWA_Lab.PrepareTests;
import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.License;
import de.hse.swa.SWA_Lab.model.Swauser;

public class ServicecontractDaoTest {
	
	private static Company company = null;
	private static Swauser swauser = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PrepareTests.initDatabase();
		company = new Company();
		company.setCompanyname("ServiceContract: TEST");
		swauser = new Swauser();
		swauser.setUsername("hans");
		swauser.setPassword("test123");
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
		ServicecontractDao s = ServicecontractDao.getInstance();
		assertNotNull(s);
	}
	
	@Test
	public void testGetServicecontract() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		Servicecontract servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(swauser);
		s.saveServicecontract(servicecontract);
		Servicecontract servicecontracts = s.getServicecontract(servicecontract.getIdservicecontract());
		assertNotNull(servicecontracts);
		
	}
	
	@Test
	public void testGetServicecontracts() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		Servicecontract servicecontract;
		for (int i = 0; i < 10; i++) {
			servicecontract = new Servicecontract();
			servicecontract.setCompany(company);
			servicecontract.setSwauser(swauser);
			s.saveServicecontract(servicecontract);
		}
		List<Servicecontract> servicecontracts = s.getServicecontracts();
		assertTrue(servicecontracts.size()>=10);		
	}
	
	@Test
	public void testSaveServicecontract() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		Servicecontract servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(swauser);
		s.saveServicecontract(servicecontract);
		Servicecontract servicecontracts = s.getServicecontract(servicecontract.getIdservicecontract());
		assertNotNull(servicecontracts);
	}
	
	@Test
	public void testDeleteServicecontracts() {
		ServicecontractDao s = ServicecontractDao.getInstance();
		List<Servicecontract> servicecontracts = s.getServicecontracts();
		for (Servicecontract servicecontract: servicecontracts) {
			LicenseDao l = LicenseDao.getInstance();
			List<License> licenses = l.getLicensesById(servicecontract.getIdservicecontract());
			for (License license: licenses) {
				l.deleteLicense(license.getId().getIdlicense());
			}
			s.deleteServicecontract(servicecontract.getIdservicecontract());
		}
	}
	
	@Test
	public void testGetServicecontractsByCompanyId() {
		Servicecontract servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(swauser);
		ServicecontractDao.getInstance().saveServicecontract(servicecontract);
		

		Company company2 = new Company();
		company2.setCompanyname("ServiceContract: TEST1");
		CompanyDao.getInstance().saveCompany(company2);
		Swauser swauser2 = new Swauser();
		swauser2.setUsername("hans1");
		swauser2.setPassword("test1234");
		UserDao.getInstance().saveSwauser(swauser2);
		
		Servicecontract servicecontract2 = new Servicecontract();
		servicecontract2.setCompany(company2);
		servicecontract2.setSwauser(swauser2);
		ServicecontractDao.getInstance().saveServicecontract(servicecontract2);
		
		List<Servicecontract> list = ServicecontractDao.getInstance().getSerivecontractByCompanyId(company2.getIdcompany());
		
		assertEquals(list.size(), 1);
		
	}
	
	@Test
	public void testGetServicecontractsByUserId() {
		Servicecontract servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(swauser);
		ServicecontractDao.getInstance().saveServicecontract(servicecontract);
		

		Company company3 = new Company();
		company3.setCompanyname("ServiceContract: TEST2");
		CompanyDao.getInstance().saveCompany(company3);
		Swauser swauser3 = new Swauser();
		swauser3.setUsername("hans2");
		swauser3.setPassword("test12345");
		UserDao.getInstance().saveSwauser(swauser3);
		
		Servicecontract servicecontract3 = new Servicecontract();
		servicecontract3.setCompany(company3);
		servicecontract3.setSwauser(swauser3);
		ServicecontractDao.getInstance().saveServicecontract(servicecontract3);
		
		List<Servicecontract> list = ServicecontractDao.getInstance().getSerivecontractByUserId(swauser3.getIduser());
		
		assertEquals(list.size(), 1);
	}
	
}
