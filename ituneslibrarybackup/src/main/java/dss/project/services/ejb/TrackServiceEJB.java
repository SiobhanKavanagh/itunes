package dss.project.services.ejb;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.TrackDAO;
import dss.project.entities.Track;
import dss.project.services.TrackService;

@Stateless
@Local
public class TrackServiceEJB implements TrackService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private TrackDAO dao;
	
	@Override
	public Collection<Track> getTracks() {
			return dao.getAllTracks();
	}

	@Override
	public void addTracks(Collection<Track> tracks) {
		dao.batchInsertTracks(tracks);
		
	}

	

}
