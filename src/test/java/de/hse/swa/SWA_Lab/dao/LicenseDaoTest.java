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
import de.hse.swa.SWA_Lab.model.License;
import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Swauser;

public class LicenseDaoTest {
	
	private static Servicecontract servicecontract = null;
	private static Company company = null;
	private static Swauser user = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		PrepareTests.initDatabase();
		company = new Company();
		company.setCompanyname("NoMorePlease");
		CompanyDao.getInstance().saveCompany(company);
		user = new Swauser();
		user.setUsername("hollahop");
		user.setPassword("hullahuii");
		user.setCompany(company);
		UserDao.getInstance().saveSwauser(user);
		servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(user);
		ServicecontractDao.getInstance().saveServicecontract(servicecontract);
		
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
		LicenseDao l = LicenseDao.getInstance();
		assertNotNull(l);
	}
	
	@Test
	public void testGetLicense() {
		LicenseDao l = LicenseDao.getInstance();
		License license = new License();
		license.setServicecontract(servicecontract);
		license.setLicensekey("ThisKeyIsSecret");
		l.saveLicense(license);
		License licenses = l.getLicense(license.getId());
		assertNotNull(licenses);		
	}
	
	@Test
	public void testGetLicenses() {
		LicenseDao l = LicenseDao.getInstance();
		License license;
		
		for (int i = 0; i < 10; i++) {
			license = new License();
			license.setServicecontract(servicecontract);
			license.setLicensekey("ThisKeyIsSecret" + i);
			l.saveLicense(license);
		}
		List<License> licenses = l.getLicenses();
		assertTrue(licenses.size()>=10);		
	}
	
	@Test
	public void testSaveLicense() {
		LicenseDao l = LicenseDao.getInstance();
		License license = new License();
		license.setServicecontract(servicecontract);
		license.setLicensekey("ThisKeyIsSecret");
		license.setLicensecount(5);
		license.setIp1("127.0.0.1");
		license.setIp2("192.168.178.127");
		license.setIp3("192.168.178.126");
		license.setIp4("192.168.178.125");
		l.saveLicense(license);
		License licenses = l.getLicense(license.getId());
		assertNotNull(licenses);
	}
	


	/*
	 * CompanyDao c = CompanyDao.getInstance();
		List<Company> companies = c.getCompanies();
		for (Company company: companies) {
			c.deleteCompany(company.getIdcompany());
		}
		
		companies = c.getCompanies();
		assertTrue(companies.size()<1);*/
	
	@Test
	public void testGetLicensesByServicecontractId() {
		License license = new License();
		license.setServicecontract(servicecontract);
		license.setLicensekey("ThisKeyIsSecret");
		license.setLicensecount(5);
		license.setIp1("127.0.0.1");
		license.setIp2("192.168.178.127");
		license.setIp3("192.168.178.126");
		license.setIp4("192.168.178.125");
		LicenseDao.getInstance().saveLicense(license);
		License license2 = new License();
		license2.setServicecontract(servicecontract);
		license2.setLicensekey("ThisKeyIsSecret2");
		license2.setLicensecount(6);
		license2.setIp1("127.0.0.2");
		license2.setIp2("192.168.178.5");
		license2.setIp3("192.168.178.6");
		license2.setIp4("192.168.178.7");
		LicenseDao.getInstance().saveLicense(license2);
		
		List<License> list = LicenseDao.getInstance().getLicensesById(servicecontract.getIdservicecontract());
		assertEquals(list.size(), 14);		
	}
	
	public void testDeleteLicenses() {
		LicenseDao l = LicenseDao.getInstance();
		List<License> licenses = l.getLicenses();
		for (License license: licenses) {
			l.deleteLicense(license.getId());
		}
		licenses = l.getLicenses();
		assertTrue(licenses.size()<1);
	}
}
