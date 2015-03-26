package dss.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.TrackDAO;
import dss.project.entities.Track;

@Local
@Stateless
public class JPATrackDAO implements TrackDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Track> getAllTracks() {
		return em.createNamedQuery("findAllTracks").getResultList();
	}

	@Override
	public Track getTrack(int trackId, String trackName, String artist,
			String album, String genre, int trackNumber, int trackPersistentId,
			String libraryPersistentIdTrack) {
		return em.find(Track.class, new Track(trackId, trackName, artist,
			album, genre, trackNumber, trackPersistentId,
			libraryPersistentIdTrack));
	}

//insert new track
	@Override
	public void insertTrack(Track track) {
		em.persist(track);
		
	}
//batch insert track
	@Override
	public void batchInsertTracks(Collection<Track> trackList) {
		for(Track track: trackList)
			em.persist(track);
	}


}
