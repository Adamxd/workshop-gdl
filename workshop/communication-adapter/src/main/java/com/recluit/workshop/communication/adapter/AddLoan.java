package com.recluit.workshop.communication.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/addloan")
public class AddLoan {
	@GET
	@Path("/{details}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String addLoan(@PathParam("details") String details){
		Connect connect = new Connect();
		return connect.establishConnection("a"+details);
	}
}
