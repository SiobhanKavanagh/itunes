package dss.project.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	//PK library Persistent ID
	@Id
	@Column(name = "library_persistent_id_user")
	private String libraryPersistentId;
	@Column(length = 32)
	private String username;
	@Column(length = 64)
	private String password;
	@Column(length = 32)
	private String role = "user";


	//relationship with tracks
	@OneToMany(mappedBy="user")
	private Collection <Track> tracks;
	
	//relationship with playlists
	@OneToMany(mappedBy="user")
	private Collection <Playlist> playlists;
	


	/**
	 * No-args constructor used by the JPA.
	 */
	public User() {
	}

	public User(String libraryPersistentId, String username, String password) {
		this.libraryPersistentId = libraryPersistentId;
		this.username = username;
		this.password = password;
		}

	public String getLibraryPersistentId() {
		return libraryPersistentId;
	}

	public void setLibraryPersistentId(String libraryPersistentId) {
		this.libraryPersistentId = libraryPersistentId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getRole() {
		return role;
	}

	public void setTracks(Collection<Track> tracks) {
		this.tracks = tracks;
	}

	public void setPlaylists(Collection<Playlist> playlists) {
		this.playlists = playlists;
	}
		
}
