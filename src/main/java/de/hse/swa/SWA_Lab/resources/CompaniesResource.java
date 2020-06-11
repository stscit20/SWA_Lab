package de.hse.swa.SWA_Lab.resources;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.model.Company;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/companies")
public class CompaniesResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    Integer id;


    /*public CompaniesResource(UriInfo uriInfo, Request request, Integer id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }*/


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void newCompanies(List<Company> companies){
        for(Company company : companies) {
            CompanyDao.getInstance().saveCompany(company);
        }
    }

    @GET
    @Path("/getallcompanies")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Company> getAllCompanies(){
        List<Company> companies = CompanyDao.getInstance().getCompanies();
        if(companies==null)
            throw new RuntimeException("No Company found!");
        return companies;
    }

    @GET
    @Path("/getallcompanies")
    @Produces(MediaType.TEXT_XML)
    public List<Company> getAllCompaniesHTML(){
        List<Company> companies = CompanyDao.getInstance().getCompanies();
        if(companies==null)
            throw new RuntimeException("No Company found!");
        return companies;
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = CompanyDao.getInstance().getCompanies().size();
        return String.valueOf(count);
    }
}
