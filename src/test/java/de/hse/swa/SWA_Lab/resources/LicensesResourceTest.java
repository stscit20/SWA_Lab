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


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LicensesResourceTest {
    private static LicenseDao l;


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
    public void testGetLicenses(){
        LicensesResource licensesResource = new LicensesResource();
        int count1 = licensesResource.getAllLicenses().size();

        Company company = new Company();
        company.setCompanyname("NoMorePlease");
        CompanyDao.getInstance().saveCompany(company);
        Swauser user = new Swauser();
        user.setUsername("hollahop");
        user.setPassword("hullahuii");
        user.setCompany(company);
        UserDao.getInstance().saveSwauser(user);
        Servicecontract servicecontract = new Servicecontract();
        servicecontract.setCompany(company);
        servicecontract.setSwauser(user);
        ServicecontractDao.getInstance().saveServicecontract(servicecontract);

        License license = new License();
        license.setServicecontract(servicecontract);
        license.setIp1("192.168.178.98");
        license.setLicensekey("asudezt7asftz");
        LicenseDao.getInstance().saveLicense(license);

        int count2 = licensesResource.getAllLicenses().size();
        assertEquals(count2-count1, 1);
    }

    @Test
    public void testGetCount(){
        LicensesResource licensesResource = new LicensesResource();
        int count1 = Integer.parseInt(licensesResource.getCount());

        Company company = new Company();
        company.setCompanyname("NoMorePlease");
        CompanyDao.getInstance().saveCompany(company);
        Swauser user = new Swauser();
        user.setUsername("hollahop");
        user.setPassword("hullahuii");
        user.setCompany(company);
        UserDao.getInstance().saveSwauser(user);
        Servicecontract servicecontract = new Servicecontract();
        servicecontract.setCompany(company);
        servicecontract.setSwauser(user);
        ServicecontractDao.getInstance().saveServicecontract(servicecontract);

        License license = new License();
        license.setServicecontract(servicecontract);
        license.setIp1("192.168.178.98");
        license.setLicensekey("asudezt7asftz");
        LicenseDao.getInstance().saveLicense(license);

        int count2 = Integer.parseInt(licensesResource.getCount());

        assertEquals(count2-count1, 1);
    }

    @Test
    public void testNewLicenses(){
        LicensesResource licensesResource = new LicensesResource();
        License license;

        Company company = new Company();
        company.setCompanyname("NoMorePlease");
        CompanyDao.getInstance().saveCompany(company);
        Swauser user = new Swauser();
        user.setUsername("hollahop");
        user.setPassword("hullahuii");
        user.setCompany(company);
        UserDao.getInstance().saveSwauser(user);
        Servicecontract servicecontract = new Servicecontract();
        servicecontract.setCompany(company);
        servicecontract.setSwauser(user);
        ServicecontractDao.getInstance().saveServicecontract(servicecontract);

        List<License> licenses = new ArrayList<License>();

        for(int i = 0; i<10; i++){
            license = new License();
            license.setServicecontract(servicecontract);
            license.setIp1("192.168.178." + i);
            license.setLicensekey("a8zsre78z6732z4" + i);
            licenses.add(license);
        }

        int count1 = licensesResource.getAllLicenses().size();

        licensesResource.newLicenses(licenses);

        int count2 = licensesResource.getAllLicenses().size();

        assertEquals(count2-licenses.size(), count1);
    }
}
