package dss.project.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.PlaylistDAO;
import dss.project.entities.Playlist;
import dss.project.entities.Track;

public class JPAPlaylistDAO implements PlaylistDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Playlist> getAllPlaylists() {
		return em.createNamedQuery("getAllPlaylists").getResultList();
	}

	@Override
	public Playlist getPlaylist(int playlistId, String playlistPersistentId,
			String libraryPersistentIdTrackPlaylist) {
		return em.find(Playlist.class, new Playlist(playlistId, playlistPersistentId));
	}

	@Override
	public void batchInsertPlaylists(Collection<Playlist> playlistList) {
		for (Playlist playlist : playlistList) {
			em.persist(playlist);
		}
	}

	

	

}
