package de.hse.swa.SWA_Lab.resources;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.UserDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Swauser;

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
    @POST
	private Response putAndGetResponse(Company company) {
		Response res;
		CompanyDao.getInstance().saveCompany(company);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
    
    
    @POST
    @Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> companyCreate(Company company, @Context HttpServletResponse servletResponse){
		Company company1 = new Company();
		    company1.setCompanyname(company.getCompanyname());		
		    company1.setAddress(company.getAddress());
		    company1.setDepartment(company.getDepartment());
		    
		    CompanyDao.getInstance().saveCompany(company1);
		
		    
		Map<String, Object> response = new HashMap<>();
		response.put("success", company1 != null);
		response.put("company", company1);
		return response;
	}
		
		
		/*
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
	}*/
}
