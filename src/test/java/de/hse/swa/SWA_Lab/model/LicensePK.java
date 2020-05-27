package de.hse.swa.SWA_Lab.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the license database table.
 * 
 */
@Embeddable
public class LicensePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idlicense;

	@Column(name="servicecontract_idservicecontract", insertable=false, updatable=false)
	private int servicecontractIdservicecontract;

	public LicensePK() {
	}
	public int getIdlicense() {
		return this.idlicense;
	}
	public void setIdlicense(int idlicense) {
		this.idlicense = idlicense;
	}
	public int getServicecontractIdservicecontract() {
		return this.servicecontractIdservicecontract;
	}
	public void setServicecontractIdservicecontract(int servicecontractIdservicecontract) {
		this.servicecontractIdservicecontract = servicecontractIdservicecontract;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LicensePK)) {
			return false;
		}
		LicensePK castOther = (LicensePK)other;
		return 
			(this.idlicense == castOther.idlicense)
			&& (this.servicecontractIdservicecontract == castOther.servicecontractIdservicecontract);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idlicense;
		hash = hash * prime + this.servicecontractIdservicecontract;
		
		return hash;
	}
}