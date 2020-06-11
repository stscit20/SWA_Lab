package de.hse.swa.SWA_Lab.resources;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Swauser;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

public class CompanyResourceTest {
    private static Company company;
    private static CompanyResource companyResource;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{

    }

    @Before
    public void setUp() throws Exception{
        Company company = new Company();
        company.setAddress("Nicolas-Lange-Straße 2");
        company.setCompanyname("Nicolas Industries");
        company.setDepartment("9375");
        CompanyDao.getInstance().saveCompany(company);
    }

    @After
    public void tearDown() throws Exception{

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetCompany(){
        assertEquals(companyResource.getCompany(company.getIdcompany()), company);
    }

    @Test
    public void testGetCompanyHtml(){
        try{
            companyResource.getCompanyHTML(company.getIdcompany());
        }
        catch (RuntimeException e){
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testDeleteCompany(){
        companyResource.deleteCompany(company.getIdcompany());
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Get: Company with " + company.getIdcompany() + " not found");
        companyResource.getCompanyHTML(company.getIdcompany());
    }


    /*Put company?*/
}
