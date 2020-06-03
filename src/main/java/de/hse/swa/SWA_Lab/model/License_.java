package de.hse.swa.SWA_Lab.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-06-03T09:58:49.069+0200")
@StaticMetamodel(License.class)
public class License_ {
	public static volatile SingularAttribute<License, LicensePK> id;
	public static volatile SingularAttribute<License, Date> expirationdate;
	public static volatile SingularAttribute<License, String> ip1;
	public static volatile SingularAttribute<License, String> ip2;
	public static volatile SingularAttribute<License, String> ip3;
	public static volatile SingularAttribute<License, String> ip4;
	public static volatile SingularAttribute<License, Integer> licensecount;
	public static volatile SingularAttribute<License, String> licensekey;
	public static volatile SingularAttribute<License, Servicecontract> servicecontract;
}
