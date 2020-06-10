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


import de.hse.swa.SWA_Lab.dao.UserDao;
import de.hse.swa.SWA_Lab.model.Swauser;

public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public UserResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Swauser getSwauser() {
		Swauser user = UserDao.getInstance().getSwauser(id);
		if(user==null)
			throw new RuntimeException("Get: Swauser with " + id +  " not found");
		return user;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Swauser getSwauserHTML() {
		Swauser user = UserDao.getInstance().getSwauser(id);
		if(user==null)
			throw new RuntimeException("Get: Swauser with " + id +  " not found");
		return user;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putSwauser(JAXBElement<Swauser> user) {
		Swauser c = user.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteSwauser() {
		UserDao.getInstance().deleteSwauser(id);
	}

	private Response putAndGetResponse(Swauser user) {
		Response res;
		UserDao.getInstance().saveSwauser(user);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
}