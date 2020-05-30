package de.hse.swa.SWA_Lab;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.hse.swa.SWA_Lab.dao.*;
// import de.hse.swa.SWA_Lab.resources.*; TODO!

@RunWith(Suite.class)
@SuiteClasses({
	// Test classes
	DaoManagerTest.class,
	CompanyDaoTest.class,
	LicenseDaoTest.class,
	ServicecontractDaoTest.class,
	SwauserDaoTest.class	
})

public class AllTests {

}
