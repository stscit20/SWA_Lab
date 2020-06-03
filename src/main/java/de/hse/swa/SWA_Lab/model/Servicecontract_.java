package de.hse.swa.SWA_Lab.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-06-03T11:02:09.458+0200")
@StaticMetamodel(Servicecontract.class)
public class Servicecontract_ {
	public static volatile SingularAttribute<Servicecontract, Integer> idservicecontract;
	public static volatile SingularAttribute<Servicecontract, Date> enddate;
	public static volatile SingularAttribute<Servicecontract, Date> startdate;
	public static volatile ListAttribute<Servicecontract, License> licenses;
	public static volatile SingularAttribute<Servicecontract, Company> company;
	public static volatile SingularAttribute<Servicecontract, Swauser> swauser;
}
