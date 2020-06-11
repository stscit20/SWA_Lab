package de.hse.swa.SWA_Lab.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;


// -> Path -> /company/2
@Path("/company")
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
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Company getCompany(){
		Company Company = CompanyDao.getInstance().getCompany(id);
		if(Company==null)
			throw new RuntimeException("Get: Company with " + id +  " not found");
		return Company;
	}

	// for the browser
	@GET
	@Path("/{id}")
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
	@Path("/{id}")
	public void deleteCompany() {
		CompanyDao.getInstance().deleteCompany(id);
	}

	private Response putAndGetResponse(Company Company) {
		Response res;
		CompanyDao.getInstance().saveCompany(Company);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
	/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void changeCompany(){
		if(CompanyDao.getInstance().getCompany(id) != null) {
			CompanyDao.getInstance().deleteCompany(id);
		}
		Company c = new Company();
		c.setIdcompany(id);
		CompanyDao.getInstance().saveCompany(c);
	}*/
}
