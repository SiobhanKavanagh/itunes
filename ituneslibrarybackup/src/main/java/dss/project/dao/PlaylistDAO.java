package dss.project.dao;

import java.util.Collection;

import dss.project.entities.Playlist;
import dss.project.entities.Track;

public interface PlaylistDAO {

	public Collection <Playlist> getallPlaylists();
	
	public Playlist getPlaylist(int playlistId, String playlistPersistentId, String libraryPersistentIdTrackPlaylist);

	public void batchInsertPlaylists(Collection <Playlist> playlistList);
	
	
}
