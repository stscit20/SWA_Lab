package de.hse.swa.SWA_Lab.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;



import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;
public class CompanyResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public CompanyResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Company getCompany() {
		Company Company = CompanyDao.getInstance().getCompany(id);
		if(Company==null)
			throw new RuntimeException("Get: Company with " + id +  " not found");
		return Company;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Company getCompanyHTML() {
		Company Company = CompanyDao.getInstance().getCompany(id);
		if(Company==null)
			throw new RuntimeException("Get: Company with " + id +  " not found");
		return Company;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putCompany(JAXBElement<Company> Company) {
		Company c = Company.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteCompany() {
		CompanyDao.getInstance().deleteCompany(id);
	}

	private Response putAndGetResponse(Company Company) {
		Response res;
		CompanyDao.getInstance().saveCompany(Company);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
}
