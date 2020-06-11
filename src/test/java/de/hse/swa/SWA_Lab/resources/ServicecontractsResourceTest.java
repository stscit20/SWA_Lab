package de.hse.swa.SWA_Lab.resources;

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
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.model.Swauser;
import de.hse.swa.SWA_Lab.resources.*;
import de.hse.swa.SWA_Lab.dao.*;


public class ServicecontractsResourceTest {
	private static Company company = null;
	private static Swauser swauser = null;
	
	private static ServicecontractDao s;
	
@BeforeClass
public static void setUpBeforeClass() throws Exception {
	s = ServicecontractDao.getInstance();
    company = new Company();
	company.setCompanyname("TestCompany");
	CompanyDao.getInstance().saveCompany(company);
	swauser = new Swauser();
	swauser.setUsername("Test Name");
	swauser.setPassword("test");
	swauser.setCompany(company);

    UserDao.getInstance().saveSwauser(swauser);
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
public void testGetServicecontracts(){
    ServicecontractsResource servicecontractsResource = new ServicecontractsResource();
    int count1 = servicecontractsResource.getAllServicecontract().size();
    Servicecontract servicecontract= new Servicecontract();
     servicecontract.setCompany(company);
     servicecontract.setSwauser(swauser);
    ServicecontractDao.getInstance().saveServicecontract(servicecontract);

    int count2 = servicecontractsResource.getAllServicecontract().size();
    assertEquals(count2-count1, 1);
}

@Test
public void testGetCount(){
    ServicecontractsResource ServicecontractsResource = new ServicecontractsResource();
    int count1 = Integer.parseInt(ServicecontractsResource.getCount());
    Servicecontract servicecontract= new Servicecontract();
    servicecontract.setCompany(company);
    servicecontract.setSwauser(swauser);
    ServicecontractDao.getInstance().saveServicecontract(servicecontract);
    CompanyDao.getInstance().saveCompany(company);

    int count2 = Integer.parseInt(ServicecontractsResource.getCount());
    assertEquals(count2-count1, 1);
}
@Test
public void testNewServicecontracts(){
	
	ServicecontractsResource servicecontractsResource = new ServicecontractsResource();
	Servicecontract servicecontract;
	List<Servicecontract> servicecontracts =new ArrayList<Servicecontract>();
	for (int i = 0; i < 10; i++) {
		servicecontract = new Servicecontract();
		servicecontract.setCompany(company);
		servicecontract.setSwauser(swauser);
		servicecontracts.add(servicecontract);
	}
	int count1 = servicecontractsResource.getAllServicecontract().size();
	
	servicecontractsResource.newServicecontracts(servicecontracts);
	int count2=servicecontractsResource.getAllServicecontract().size();
	assertEquals(count2 - servicecontracts.size(),count1);		
}



/*public void testGetServicecontract() {
	ServicecontractDao s = ServicecontractDao.getInstance();
	Servicecontract servicecontract = new Servicecontract();
   
	servicecontract.setCompany(company);
	servicecontract.setSwauser(swauser);
	s.saveServicecontract(servicecontract);
	Servicecontract servicecontracts = s.getServicecontract(servicecontract.getIdservicecontract());
	assertNotNull(servicecontracts);
	
}*/

}