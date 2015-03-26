package dss.project.services.ejb;

import java.util.Collection;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dss.project.dao.PlaylistDAO;
import dss.project.entities.Playlist;
import dss.project.services.PlaylistService;

@Local
public class PlaylistServiceEJB implements PlaylistService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private PlaylistDAO dao;

	@Override
	public Collection<Playlist> getPlaylists() {
		return dao.getallPlaylists();
	}

	@Override
	public void addPlaylists(Collection<Playlist> playlists) {
		dao.batchInsertPlaylists(playlists);
		
	}
	
	

}
