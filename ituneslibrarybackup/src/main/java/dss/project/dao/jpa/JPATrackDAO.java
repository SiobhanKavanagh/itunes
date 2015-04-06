package dss.project.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.TrackDAO;
import dss.project.entities.Track;


public class JPATrackDAO implements TrackDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Track> getAllTracks() {
		return em.createNamedQuery("findAllTracks").getResultList();
	}

//insert new track
	@Override
	public void insertTrack(Track track) {
		em.persist(track);
		
	}


	@Override
	public Track getTrack(int trackId, String trackName, String artist,
			String album, String genre, int trackNumber, String trackPersistentId,
			String libraryPersistentIdTrack) {
		return em.find(Track.class, new Track(trackId, trackName, artist,
				album, genre, trackNumber, trackPersistentId,
				libraryPersistentIdTrack));
	}

	@Override
	public void batchInsertTracks(Collection <Track> trackList) {
		for (Track track : trackList) {
			em.persist(track);
		}
	}


}
