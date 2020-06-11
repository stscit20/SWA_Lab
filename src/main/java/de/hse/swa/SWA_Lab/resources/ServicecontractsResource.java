package de.hse.swa.SWA_Lab.resources;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.ServicecontractDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Servicecontract;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class ServicecontractsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    Integer id;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void newServicecontracts(List<Servicecontract> servicecontracts){
        for(Servicecontract servicecontract : servicecontracts) {
            ServicecontractDao.getInstance().saveServicecontract(servicecontract);
        }
    }

    @GET
    @Path("/getallservicecontracts")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Servicecontract> getAllServicecontract(){
        List<Servicecontract> servicecontracts = ServicecontractDao.getInstance().getServicecontracts();
        if(servicecontracts==null)
            throw new RuntimeException("No Servicecontract found!");
        return servicecontracts;
    }

    @GET
    @Path("/getallservicecontracts")
    @Produces(MediaType.TEXT_XML)
    public List<Servicecontract> getAllServicecontractHTML(){
        List<Servicecontract> servicecontracts = ServicecontractDao.getInstance().getServicecontracts();
        if(servicecontracts==null)
            throw new RuntimeException("No Servicecontract found!");
        return servicecontracts;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = ServicecontractDao.getInstance().getServicecontracts().size();
        return String.valueOf(count);
    }
}
