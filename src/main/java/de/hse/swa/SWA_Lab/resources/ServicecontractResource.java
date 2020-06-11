package de.hse.swa.SWA_Lab.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.ServicecontractDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Servicecontract;

@Path("/servicecontract")
public class ServicecontractResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    Integer id;

    /*public ServicecontractResource(UriInfo uriInfo, Request request, Integer id){
        this.uriInfo  = uriInfo;
        this.request = request;
        this.id = id;
    }*/

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Servicecontract getServicecontract(@PathParam("id") Integer id){
        Servicecontract servicecontract = ServicecontractDao.getInstance().getServicecontract(id);
        if(servicecontract==null)
            throw new RuntimeException("Get: Company with " + id +  " not found");
        return servicecontract;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_XML)
    public Servicecontract getServicecontractHTML(@PathParam("id") Integer id){
        Servicecontract servicecontract = ServicecontractDao.getInstance().getServicecontract(id);
        if(servicecontract==null)
            throw new RuntimeException("Get: Company with " + id +  " not found");
        return servicecontract;
    }
}
