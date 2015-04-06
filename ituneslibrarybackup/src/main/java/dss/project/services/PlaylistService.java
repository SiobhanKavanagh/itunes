package dss.project.services;

import java.util.Collection;

import javax.ejb.Local;

import dss.project.entities.Playlist;
import dss.project.entities.Track;

@Local
public interface PlaylistService {

	public Collection <Playlist> getPlaylists();
	
	public void addPlaylists(Collection <Playlist> playlists);
}
