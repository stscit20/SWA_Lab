package de.hse.swa.SWA_Lab.resources;

import de.hse.swa.SWA_Lab.PrepareTests;
import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.LicenseDao;
import de.hse.swa.SWA_Lab.dao.ServicecontractDao;
import de.hse.swa.SWA_Lab.dao.UserDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.License;
import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.model.Swauser;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LicenseResourceTest {

    private static Servicecontract servicecontract = null;
    private static Company company = null;
    private static Swauser user = null;
    private static License license = null;

    private static LicenseResource licenseResource;

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

        license = new License();
        license.setServicecontract(servicecontract);
        license.setIp1("192.168.178.98");
        license.setLicensekey("asudezt7asftz");
        LicenseDao.getInstance().saveLicense(license);

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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetLicense(){
        assertEquals(licenseResource.getLicense(license.getId()), license);
    }

    @Test
    public void testGetLicenseHtml(){
        try{
            licenseResource.getLicenseHTML(license.getId());
        }
        catch (RuntimeException e){
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testDeleteLicense(){
        licenseResource.deleteLicense(license.getId());
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Get: License with " + license.getId() + " not found");
        licenseResource.getLicenseHTML(license.getId());
    }

}
