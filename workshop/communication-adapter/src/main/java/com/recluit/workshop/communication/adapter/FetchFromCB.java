package com.recluit.workshop.communication.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/searchrfc")
public class FetchFromCB {

	@GET
	@Path("/{rfc}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String searchRFC(@PathParam("rfc") String rfc){
		Connect connect = new Connect();
		return connect.establishConnection("f"+rfc);
	}
}		