package de.hse.swa.SWA_Lab.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-06-03T11:02:09.455+0200")
@StaticMetamodel(Company.class)
public class Company_ {
	public static volatile SingularAttribute<Company, Integer> idcompany;
	public static volatile SingularAttribute<Company, String> address;
	public static volatile SingularAttribute<Company, String> companyname;
	public static volatile SingularAttribute<Company, String> department;
	public static volatile ListAttribute<Company, Servicecontract> servicecontracts;
	public static volatile ListAttribute<Company, Swauser> swausers;
}
