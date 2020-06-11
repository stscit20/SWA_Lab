package de.hse.swa.SWA_Lab.resources;

import de.hse.swa.SWA_Lab.dao.LicenseDao;
import de.hse.swa.SWA_Lab.model.License;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;

@Path("/license")
public class LicenseResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    Integer id;


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public License getLicense(@PathParam("id") Integer id){
        License license = LicenseDao.getInstance().getLicense(id);
        if(license==null)
            throw new RuntimeException("Get: License with " + id +  " not found");
        return license;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_XML)
    public License getLicenseHTML(@PathParam("id") Integer id){
        License license = LicenseDao.getInstance().getLicense(id);
        if(license==null)
            throw new RuntimeException("Get: License with " + id +  " not found");
        return license;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putLicense(JAXBElement<License> license) {
        License l = license.getValue();
        return putAndGetResponse(l);
    }

    @DELETE
    @Path("{id}")
    public void deleteLicense(@PathParam("id") Integer id) {
        LicenseDao.getInstance().deleteLicense(id);
    }

    private Response putAndGetResponse(License license) {
        Response res;
        LicenseDao.getInstance().saveLicense(license);
        res = Response.created(uriInfo.getAbsolutePath()).build();
        return res;
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeLicense(@PathParam("id") Integer id){
        if(LicenseDao.getInstance().getLicense(id) != null) {
            LicenseDao.getInstance().deleteLicense(id);
        }
        License l = new License();
        l.setId(id);
        LicenseDao.getInstance().saveLicense(l);
    }
}
