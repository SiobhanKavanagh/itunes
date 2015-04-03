package dss.project.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {
	
	//PK playlist id
	@Id
	@Column(length = 50, name = "playlist_id")
	private int playlistId;
	@Column(length = 32, name = "playlist_persistent_id")
	private String playlistPersistentId;
	@Column(length = 32, name = "playlist_name")
	private String playlistName;
	//FK of User
	@Column(length = 32, name = "library_persistent_id_playlist")
	private String libraryPersistentIdTrackPlaylist;
	
	//many to many relationship with track
	@ManyToMany
	@JoinTable(name = "playlist_has_track", joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "playlist_id"),
			inverseJoinColumns= @JoinColumn(name = "track_id", referencedColumnName = "track_id"))
	private Collection <Track> tracks;
	
	
	/**
	 * No-args constructor used by the JPA.
	 */
	public Playlist(){
		
	}
		
	public Playlist(int playlistId, String playlistPersistentId, String libraryPersistentIdTrackPlaylist) {
		this.playlistId = playlistId;
		this.playlistPersistentId = playlistPersistentId;
		this.libraryPersistentIdTrackPlaylist =  libraryPersistentIdTrackPlaylist;
	}

	public int getPlaylistId() {
		return playlistId;
	}
	
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}
	
	public String getplaylistPersistentId() {
		return playlistPersistentId;
	}
	
	public void setplaylistPersistentId(String playlistPersistentId) {
		this.playlistPersistentId = playlistPersistentId;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public Collection<Track> getTracks() {
		return tracks;
	}
	
	public void setTracks(Collection<Track> tracks) {
		this.tracks = tracks;
	}
	
	
	

}
