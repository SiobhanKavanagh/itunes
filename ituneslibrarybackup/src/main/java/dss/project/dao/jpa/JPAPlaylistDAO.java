package dss.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dss.project.dao.PlaylistDAO;
import dss.project.entities.Playlist;

public class JPAPlaylistDAO implements PlaylistDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Playlist> getallPlaylists() {
		return em.createNamedQuery("findAllPlaylists").getResultList();
	}

	@Override
	public Playlist getPlaylist(int playlistId, String playlistPersistentId,
			String libraryPersistentIdTrackPlaylist) {
		return em.find(Playlist.class, new Playlist(playlistId, playlistPersistentId,
			libraryPersistentIdTrackPlaylist));
	}

	@Override
	public void batchInsertPlaylists(Collection<Playlist> playlistList) {
		for(Playlist playlist: playlistList)
			em.persist(playlist);
	}

}
