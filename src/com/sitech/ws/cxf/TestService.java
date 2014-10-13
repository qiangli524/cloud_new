package com.sitech.ws.cxf;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TestService {
	@GET
	@Path("/connect")
	public String connect();

	@GET
	@Path("/{vType}/testMonitor")
	public MoMonitorVO doGetMonitor(@PathParam("vType") String vType);
}
