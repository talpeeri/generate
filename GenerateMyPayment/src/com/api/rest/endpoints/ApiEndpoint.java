package com.api.rest.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.db.datatypes.Pain001;
import com.api.services.ConvertorService;
import com.google.gson.Gson;

@Path("/api")
public class ApiEndpoint extends Application
{
	private static final Gson gson = new Gson();

	@Path("/payment")
	@POST
	@Consumes({"text/plain,text/html"})
	@Produces(MediaType.TEXT_XML)
	public Response textToPayment(String text){
		ConvertorService covertorService = new ConvertorService(); 
		Pain001 p = covertorService.convertTextToPayment(text);
		return getJsonResponseFromResults(p);
	}
	

	private Response getJsonResponseFromResults(Object results) {
		String jsonResults = gson.toJson(results); 
		return Response.status(Response.Status.OK/*200*/).entity(jsonResults).build();
	}
	

}
