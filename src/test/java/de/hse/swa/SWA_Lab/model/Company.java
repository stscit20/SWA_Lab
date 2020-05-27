package de.hse.swa.SWA_Lab.model;

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

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="company")
	private List<User> users;

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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCompany(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCompany(null);

		return user;
	}

}