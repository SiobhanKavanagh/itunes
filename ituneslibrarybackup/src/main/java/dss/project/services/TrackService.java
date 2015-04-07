package dss.project.services;

import java.util.Collection;
import javax.ejb.Local;
import dss.project.entities.Track;


@Local
public interface TrackService {
	
	public Collection <Track> getTracks();
	
	public void addTracks(Collection <Track> tracks);
	
	public Collection <Track> getTracksForLibrary(String library);
}
