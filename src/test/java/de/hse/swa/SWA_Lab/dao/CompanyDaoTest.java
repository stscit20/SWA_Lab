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
import de.hse.swa.SWA_Lab.model.Company;

public class CompanyDaoTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		PrepareTests.initDatabase();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		
	}
	
	@Test
	public void testGetInstance() {
		CompanyDao c = CompanyDao.getInstance();
		assertNotNull(c);
	}
	
	@Test
	public void testGetCompany() {
		CompanyDao c = CompanyDao.getInstance();
		Company company = new Company();
		company.setCompanyname("COMPANY TEST: company name");
		c.saveCompany(company);
		Company companytest = c.getCompany(company.getIdcompany());
		assertNotNull(companytest);
	}
	
	@Test
	public void testGetCompanies() {
		CompanyDao c = CompanyDao.getInstance();
		Company company;
		for (long i=0; i < 10; ++i) {
			company = new Company();
			company.setCompanyname("COMPANY TEST No. " + i);
			c.saveCompany(company);
		}
		List<Company> companies = c.getCompanies();
		assertTrue(companies.size()>=10);
	}
	
	@Test
	public void testSaveCompany() {
		CompanyDao c = CompanyDao.getInstance();
		Company company = new Company();
		company.setCompanyname("COMPANY TEST: company name");
		company.setAddress("Saltaddress 1");
		company.setDepartment("First dep");
		c.saveCompany(company);
		// later probably add servicecontracts & users
		Company companies = c.getCompany(company.getIdcompany());
		assertNotNull(companies);
	}
	
	@Test
	public void testDeleteCompanies() {
		CompanyDao c = CompanyDao.getInstance();
		List<Company> companies = c.getCompanies();
		for (Company company: companies) {
			c.deleteCompany(company.getIdcompany());
		}
		
		companies = c.getCompanies();
		assertTrue(companies.size()<1);
	}
}
