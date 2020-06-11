package de.hse.swa.SWA_Lab.resources;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;
import org.junit.BeforeClass;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

public class CompaniesResourceTest {
    private static CompanyDao c;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception{

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
    public void testGetCompanies(){
        CompaniesResource companiesResource = new CompaniesResource();
        int count1 = companiesResource.getAllCompanies().size();
        Company company = new Company();
        company.setAddress("Nicolas-Lange-Straße 2");
        company.setCompanyname("Nicolas Industries");
        company.setDepartment("9375");
        CompanyDao.getInstance().saveCompany(company);

        int count2 = companiesResource.getAllCompanies().size();
        assertEquals(count2-count1, 1);
    }

    @Test
    public void testGetCount(){
        CompaniesResource companiesResource = new CompaniesResource();
        int count1 = Integer.parseInt(companiesResource.getCount());
        Company company = new Company();
        company.setAddress("Nicolas-Lange-Straße 2");
        company.setCompanyname("Nicolas Industries");
        company.setDepartment("9375");
        CompanyDao.getInstance().saveCompany(company);

        int count2 = Integer.parseInt(companiesResource.getCount());
        assertEquals(count2-count1, 1);
    }

    @Test
    public void testNewCompanies(){
        CompaniesResource companiesResource = new CompaniesResource();
        Company company;
        List<Company> companies = new ArrayList<Company>();

        for(int i = 0; i<10; i++){
            company = new Company();
            company.setAddress("Nicolas-Lange-Straße " + i);
            company.setCompanyname("Nicolas Industries");
            company.setDepartment("9375");
            companies.add(company);
        }

        int count1 = companiesResource.getAllCompanies().size();

        companiesResource.newCompanies(companies);

        int count2 = companiesResource.getAllCompanies().size();

        assertEquals(count2 - companies.size(), count1);
    }
}
