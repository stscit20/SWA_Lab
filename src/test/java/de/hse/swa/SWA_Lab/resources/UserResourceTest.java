package de.hse.swa.SWA_Lab.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.hse.swa.SWA_Lab.PrepareTests;
import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.model.Swauser;
import de.hse.swa.SWA_Lab.resources.*;
import de.hse.swa.SWA_Lab.dao.*;
public class UserResourceTest {


    private static Swauser user;
    private static UserResource userResource;
    private static Company company;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception{

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{

    }

    @Before
    public void setUp() throws Exception{
        PrepareTests.initDatabase();
		company = new Company();
	    company.setCompanyname("TestCompany");

	    CompanyDao.getInstance().saveCompany(company);

	    user = new Swauser();
        user.setUsername("Test");
        user.setPassword("12345");
        user.setCompany(company);
        UserDao.getInstance().saveSwauser(user);
        userResource = new UserResource();
    }
    @After
    public void tearDown() throws Exception{

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetUser(){
        assertEquals(userResource.getSwauser(user.getIduser()),user);
    }

    @Test
    public void testGetUserHtml(){
        try{
            userResource.getSwauserHTML(user.getIduser());
        }
        catch (RuntimeException e){
            assertTrue(false);
        }
        assertTrue(true);
    }
    @Test
    public void testDeleteUser(){
        userResource.deleteSwauser(user.getIduser());
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Get: Swauser with " + user.getIduser() + " not found");
        userResource.getSwauserHTML(user.getIduser());
    }

}
