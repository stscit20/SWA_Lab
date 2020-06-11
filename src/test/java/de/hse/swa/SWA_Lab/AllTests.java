package de.hse.swa.SWA_Lab;

import de.hse.swa.SWA_Lab.model.Servicecontract;
import de.hse.swa.SWA_Lab.resources.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hse.swa.SWA_Lab.dao.*;
//import de.hse.swa.SWA_Lab.resources.*; TODO!

@RunWith(Suite.class)
@SuiteClasses({
	// Test classes
		DaoManagerTest.class,
		LicenseDaoTest.class,
		ServicecontractDaoTest.class,
		SwauserDaoTest.class,
		CompanyDaoTest.class,

		
		CompaniesResourceTest.class,
		CompanyResourceTest.class,
		LicenseResourceTest.class,
		LicensesResourceTest.class,
		ServicecontractResourceTest.class,
		ServicecontractsResourceTest.class,
		UserResourceTest.class,
		UsersResourceTest.class,
})

public class AllTests {

}
