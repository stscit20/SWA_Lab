package de.hse.swa.SWA_Lab.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the servicecontract database table.
 * 
 */

@Entity
@Table(name="servicecontract")
@NamedQuery(name="Servicecontract.findAll", query="SELECT s FROM Servicecontract s")
public class Servicecontract implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
   
	@GeneratedValue(strategy=GenerationType.TABLE)
	 
	private int idservicecontract;

	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;

	//bi-directional many-to-one association to License
	@OneToMany(mappedBy="servicecontract")
	private List<License> licenses;

	//bi-directional many-to-one association to Company
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Company company;

	//bi-directional many-to-one association to Swauser
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_iduser")
	private Swauser swauser;

	public Servicecontract() {
	}

	public int getIdservicecontract() {
		return this.idservicecontract;
	}

	public void setIdservicecontract(int idservicecontract) {
		this.idservicecontract = idservicecontract;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public List<License> getLicenses() {
		return this.licenses;
	}

	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}

	public License addLicens(License licens) {
		getLicenses().add(licens);
		licens.setServicecontract(this);

		return licens;
	}

	public License removeLicens(License licens) {
		getLicenses().remove(licens);
		licens.setServicecontract(null);

		return licens;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Swauser getSwauser() {
		return this.swauser;
	}

	public void setSwauser(Swauser swauser) {
		this.swauser = swauser;
	}

}