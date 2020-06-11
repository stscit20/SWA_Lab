package de.hse.swa.SWA_Lab.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.servlet.*;

import de.hse.swa.SWA_Lab.dao.CompanyDao;
import de.hse.swa.SWA_Lab.dao.UserDao;
import de.hse.swa.SWA_Lab.model.Company;
import de.hse.swa.SWA_Lab.model.Swauser;

// Will map the resource to the URL users
@Path("/users")
@ApplicationPath("/apiv2")
public class UsersResource extends Application{

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of users to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Swauser> getSwausersBrowser() {
    return UserDao.getInstance().getSwausers();
  }

  // Return the list of users in JSON format
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<Swauser> getSwausers() {
	   return UserDao.getInstance().getSwausers();
  }

  // returns the number of users
  // to get the total number of records
  @GET
  @Path("/count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = UserDao.getInstance().getSwausers().size();
    return String.valueOf(count);
  }

  // This is the workhorse
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void newUsers(List<Swauser> users){
      for(Swauser user : users) {
          UserDao.getInstance().saveSwauser(user);
      }
  }
} 
