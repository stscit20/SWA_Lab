package de.hse.swa.SWA_Lab.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import de.hse.swa.SWA_Lab.PrepareTests;
import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.model.Swauser;
import de.hse.swa.SWA_Lab.resources.*;
import de.hse.swa.SWA_Lab.dao.*;

public class UsersResourceTest {

	    private static UserDao u;
 private static Company company = null;

	    @BeforeClass
	    public static void setUpBeforeClass() throws Exception{
	       	 PrepareTests.initDatabase();

			 company = new Company();
			 company.setCompanyname("TestCompany");

			 CompanyDao.getInstance().saveCompany(company);

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
	    public void testGetUsers(){
	        UsersResource usersResource = new UsersResource();
	        int count1 = usersResource.getSwausers().size();
	        Swauser user = new Swauser();
	        user.setUsername("Test1");
	        user.setPassword("Test");
	        user.setCompany(company);
	        UserDao.getInstance().saveSwauser(user);

	        int count2 = usersResource.getSwausers().size();
	        assertEquals(count2-count1, 1);
	    }

	    @Test
	    public void testGetCount(){
	        UsersResource usersResource = new UsersResource();
	        int count1 = Integer.parseInt(usersResource.getCount());
	        Swauser user= new Swauser();
	        user.setUsername("Test1");
	        user.setPassword("12345");
	        user.setCompany(company);
	        UserDao.getInstance().saveSwauser(user);

	        int count2 = Integer.parseInt(usersResource.getCount());
	        assertEquals(count2-count1, 1);
	    }

	    @Test
	    public void testNewUsers(){
	        UsersResource usersResource = new UsersResource();
	         Swauser user;
	        List<Swauser> users = new ArrayList<Swauser>();

	        for(int i = 0; i<10; i++){
	            user = new Swauser();
	            user.setUsername("Test" + i);
	            user.setPassword("12345");
	            user.setCompany(company);
	            users.add(user);
	        }

	        int count1 = usersResource.getSwausers().size();

	        usersResource.newUsers(users);

	        int count2 = usersResource.getSwausers().size();

	        assertEquals(count2 - users.size(), count1);
	    }
	}


