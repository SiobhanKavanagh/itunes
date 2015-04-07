package dss.project.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.simple.JSONObject;

import dss.project.entities.User;
import dss.project.services.TrackService;
import dss.project.services.UserService;

public class CreatedQueries {

	@Inject
	private UserService userService;
	
	@Inject
	private TrackService trackService;
	
	@GET
	@Path("/viewTracks")
	public Collection getTracks() {
		return trackService.getTracks();
	}
}
