package de.hse.swa.SWA_Lab;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name="company")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int idcompany;

	private String address;

	private String companyname;

	private String department;

	//bi-directional many-to-one association to Servicecontract
	@OneToMany(mappedBy="company")
	private List<Servicecontract> servicecontracts;

	//bi-directional many-to-one association to Swauser
	@OneToMany(mappedBy="company")
	private List<Swauser> swausers;

	public Company() {
	}

	public int getIdcompany() {
		return this.idcompany;
	}

	public void setIdcompany(int idcompany) {
		this.idcompany = idcompany;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Servicecontract> getServicecontracts() {
		return this.servicecontracts;
	}

	public void setServicecontracts(List<Servicecontract> servicecontracts) {
		this.servicecontracts = servicecontracts;
	}

	public Servicecontract addServicecontract(Servicecontract servicecontract) {
		getServicecontracts().add(servicecontract);
		servicecontract.setCompany(this);

		return servicecontract;
	}

	public Servicecontract removeServicecontract(Servicecontract servicecontract) {
		getServicecontracts().remove(servicecontract);
		servicecontract.setCompany(null);

		return servicecontract;
	}

	public List<Swauser> getSwausers() {
		return this.swausers;
	}

	public void setSwausers(List<Swauser> swausers) {
		this.swausers = swausers;
	}

	public Swauser addSwauser(Swauser swauser) {
		getSwausers().add(swauser);
		swauser.setCompany(this);

		return swauser;
	}

	public Swauser removeSwauser(Swauser swauser) {
		getSwausers().remove(swauser);
		swauser.setCompany(null);

		return swauser;
	}

}