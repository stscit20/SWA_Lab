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

@Path("/company")
public class CompanyResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	/*public CompanyResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}*/

	//Application integration     
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Company getCompany(@PathParam("id") Integer id){
		Company company = CompanyDao.getInstance().getCompany(id);
		if(company==null)
			throw new RuntimeException("Get: Company with " + id +  " not found");
		return company;
	}

	// for the browser
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_XML)
	public Company getCompanyHTML(@PathParam("id") Integer id) {
		Company company = CompanyDao.getInstance().getCompany(id);
		if(company==null)
			throw new RuntimeException("Get: Company with " + id +  " not found");
		return company;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putCompany(JAXBElement<Company> company) {
		Company c = company.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	@Path("{id}")
	public void deleteCompany(@PathParam("id") Integer id) {
		CompanyDao.getInstance().deleteCompany(id);
	}

	private Response putAndGetResponse(Company company) {
		Response res;
		CompanyDao.getInstance().saveCompany(company);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void changeCompany(@PathParam("id") Integer id){
		if(CompanyDao.getInstance().getCompany(id) != null) {
			CompanyDao.getInstance().deleteCompany(id);
		}
		Company c = new Company();
		c.setIdcompany(id);
		CompanyDao.getInstance().saveCompany(c);
	}
}
