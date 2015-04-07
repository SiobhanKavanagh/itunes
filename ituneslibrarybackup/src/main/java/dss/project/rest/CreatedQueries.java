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
import dss.project.services.PlaylistService;
import dss.project.services.TrackService;
import dss.project.services.UserService;

@Path("/query")
public class CreatedQueries {

	@Inject
	private UserService userService;
	
	@Inject
	private TrackService trackService;
	
	@Inject
	private PlaylistService playlistService;
	
	@POST
	@Path("/getTracks")
	public Collection getTracks() {
		return trackService.getAllTracks();
	}
	
	@POST
	@Path("/getPlaylists")
	public Collection getPlaylists() {
		return playlistService.getAllPlaylists();
	}
	

}
