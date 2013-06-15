package com.recluit.workshop.communication.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/qualification")
public class Qualification {
	@GET
	@Path("/{rfc}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String getQualification(@PathParam("rfc") String rfc){
		Connect connect = new Connect();
		return connect.establishConnection("q"+rfc);
	}
}
