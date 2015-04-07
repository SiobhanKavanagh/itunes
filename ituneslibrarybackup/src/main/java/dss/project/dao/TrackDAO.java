package dss.project.dao;

import java.util.Collection;



import dss.project.entities.Track;

public interface TrackDAO {

	public Collection <Track> getAllTracks();
	
	public Track getTrack(int trackId, String trackName, String artist, String album,
			String genre, int trackNumber, String trackPersistentId, String libraryPersistentIdTrack);
	
	public void insertTrack(Track track);
	
	public void batchInsertTracks(Collection <Track> trackList);

	public Collection<Track> getTracksForLibrary(String library);
	
	
}
