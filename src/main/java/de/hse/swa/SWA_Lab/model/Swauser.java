package de.hse.swa.SWA_Lab.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the swauser database table.
 * 
 */
@Entity
@Table(name="swauser")
@NamedQuery(name="Swauser.findAll", query="SELECT s FROM Swauser s")
public class Swauser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int iduser;

	@Column(name="create_time")
	private Timestamp createTime;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_admin")
	private byte isAdmin;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-one association to Servicecontract
	@OneToMany(mappedBy="swauser")
	private List<Servicecontract> servicecontracts;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	public Swauser() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Servicecontract> getServicecontracts() {
		return this.servicecontracts;
	}

	public void setServicecontracts(List<Servicecontract> servicecontracts) {
		this.servicecontracts = servicecontracts;
	}

	public Servicecontract addServicecontract(Servicecontract servicecontract) {
		getServicecontracts().add(servicecontract);
		servicecontract.setSwauser(this);

		return servicecontract;
	}

	public Servicecontract removeServicecontract(Servicecontract servicecontract) {
		getServicecontracts().remove(servicecontract);
		servicecontract.setSwauser(null);

		return servicecontract;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}