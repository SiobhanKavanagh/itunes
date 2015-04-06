package dss.project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
	
	//PK Track ID
	@Id
	@Column(length = 32, name = "track_id")
	private Integer trackId;
	@Column(name = "track_name")
	private String trackName;
	@Column(length = 32, name = "artist")
	private String artist;
	@Column(length = 32, name = "album")
	private String album;
	@Column(length = 32, name = "genre")
	private String genre;
	@Column(length = 32, name = "track_number")
	private int trackNumber;
	@Column(length = 32, name = "track_persistent_id")
	private String trackPersistentId;
	//FK of User
	@Column(length = 32, name = "library_persistent_id_track")
	private String libraryPersistentIdTrack;
	/**
	 * No-args constructor used by the JPA.
	 */
	public Track(){
		
	}
	
	public Track(Integer trackId, String trackName, String artist, String album,
			String genre, int trackNumber, String trackPersistentId, String libraryPersistentIdTrack) {
		this.trackId = trackId;
		this.trackName = trackName;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.trackNumber = trackNumber;
		this.trackPersistentId = trackPersistentId;
		this.libraryPersistentIdTrack = libraryPersistentIdTrack;
	}

	public Integer getTrackId() {
		return trackId;
	}
	
	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	
	public String getTrackName() {
		return trackName;
	}
	
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getTrackNumber() {
		return trackNumber;
	}
	
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	
	public String gettrackPersistentId() {
		return trackPersistentId;
	}
	
	public void settrackPersistentId(String trackPersistentId) {
		this.trackPersistentId = trackPersistentId;
	}
}
