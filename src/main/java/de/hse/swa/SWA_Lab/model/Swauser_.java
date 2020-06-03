package de.hse.swa.SWA_Lab.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-06-03T09:58:49.079+0200")
@StaticMetamodel(Swauser.class)
public class Swauser_ {
	public static volatile SingularAttribute<Swauser, Integer> iduser;
	public static volatile SingularAttribute<Swauser, Timestamp> createTime;
	public static volatile SingularAttribute<Swauser, String> email;
	public static volatile SingularAttribute<Swauser, String> firstName;
	public static volatile SingularAttribute<Swauser, Byte> isAdmin;
	public static volatile SingularAttribute<Swauser, String> lastName;
	public static volatile SingularAttribute<Swauser, String> password;
	public static volatile SingularAttribute<Swauser, String> username;
	public static volatile ListAttribute<Swauser, Servicecontract> servicecontracts;
	public static volatile SingularAttribute<Swauser, Company> company;
}
