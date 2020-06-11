package de.hse.swa.SWA_Lab.resources;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.LicenseDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.License;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/licenses")
public class LicensesResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    Integer id;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void newLicenses(List<License> licenses){
        for(License license : licenses) {
            LicenseDao.getInstance().saveLicense(license);
        }
    }

    @GET
    @Path("/getalllicenses")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<License> getAllLicenses(){
        List<License> licenses = LicenseDao.getInstance().getLicenses();
        if(licenses==null)
            throw new RuntimeException("No License found!");
        return licenses;
    }

    @GET
    @Path("/getalllicenses")
    @Produces(MediaType.TEXT_XML)
    public List<License> getAllLicensesHTML(){
        List<License> licenses = LicenseDao.getInstance().getLicenses();
        if(licenses==null)
            throw new RuntimeException("No License found!");
        return licenses;
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = LicenseDao.getInstance().getLicenses().size();
        return String.valueOf(count);
    }

}
