package dss.project.services.ejb;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import dss.project.dao.PlaylistDAO;
import dss.project.dao.TrackDAO;
import dss.project.dao.UserDAO;
import dss.project.entities.Playlist;
import dss.project.entities.Track;
import dss.project.entities.User;
import dss.project.services.DataImportService;

@Stateless
@Local
@Path("/import")
public class DataImportServiceEJB implements DataImportService {

	private static final String libraryPersistenceId = null;

	@Inject
	private UserDAO userDAO;
	@Inject
	private TrackDAO trackDAO;
	@Inject
	private PlaylistDAO playlistDAO;

	//add tracks
	private Collection<Track> tracks = new ArrayList<>();
	//add playlists
	private Collection <Playlist> playlists = new ArrayList<>();
	//add user
	User user = new User();


	public void initialiseUser(String username, String password)
	{
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
	}
	
	public void importXML(String xmlFiles) {

		try {
			//file to import
			File xmlFile = new File(xmlFiles);
			//builds the xml file
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//parses xml
			Document doc = dBuilder.parse(xmlFile);
			//normalises data
			doc.getDocumentElement().normalize();
			
			//get all <dict> nodes 
			NodeList dicts = doc.getElementsByTagName("dict");

			//USER----------
			//need library persistence id
			Node libraryPersistence = dicts.item(0);
			NodeList lpChild = libraryPersistence.getChildNodes();

			for (int j = 0; j < lpChild.getLength(); j++) {

				if(lpChild.item(j).getTextContent().equals("Library Persistent ID")){
					String libraryPersistentId = lpChild.item(j+1).getTextContent();

					user.setLibraryPersistentId(libraryPersistentId);
					//System.out.println(libraryPersistentId);
					//user.setPassword("password");
					//user.setUsername("username");
				}
			}
			
			//TRACKS-------
			//track id is first node
			Node trackNode = dicts.item(1);

			//cast node to element
			Element trackElement = (Element) trackNode;
			NodeList trackChild = trackElement.getElementsByTagName("dict");


			for (int i = 0; i < trackChild.getLength(); i++) {
				Track t = new Track();

				NodeList trackElements = trackChild.item(i).getChildNodes();
				for (int j = 0; j < trackElements.getLength(); j++) {
					if(trackElements.item(j).getTextContent().equals("Track ID")){
						int trackId = Integer.parseInt(trackElements.item(j + 1).getTextContent());
						t.setTrackId(trackId);
						//System.out.println(trackId);
					}
					if(trackElements.item(j).getTextContent().equals("Name")){
						String name = trackElements.item(j +1).getTextContent();
						t.setTrackName(name);
						//System.out.println(name);
					}
					if(trackElements.item(j).getTextContent().equals("Artist")){
						String album = trackElements.item(j +1).getTextContent();
						t.setAlbum(album);
						//System.out.println(album);
					}
					if(trackElements.item(j).getTextContent().equals("Genre")){
						String genre = trackElements.item(j +1).getTextContent();
						t.setGenre(genre);
						//System.out.println(genre);
					}
					if(trackElements.item(j).getTextContent().equals("Track Number")){
						int trackNumber = Integer.parseInt(trackElements.item(j +1).getTextContent());
						t.setTrackNumber(trackNumber);
						//System.out.println(trackNumber);
					}
					if(trackElements.item(j).getTextContent().equals("Persistent ID")){
						String persistenceId = trackElements.item(j +1).getTextContent();
						t.settrackPersistentId(persistenceId);
						//System.out.println(persistenceId);
					}
					if(t.gettrackPersistentId() != null){
						
						//add track to collection of tracks
						tracks.add(t);
						//System.out.println("added tracks");
//						trackDAO.batchInsertTracks(tracks);
					}
				}
				trackDAO.batchInsertTracks(tracks);
			}

			//PLAYLISTS--------------
			//find all <array> nodes
			NodeList arrays = doc.getElementsByTagName("array");
			//playlist name is first node
			Node playlistNode = arrays.item(0);
			Element playlistElement = (Element) playlistNode;
			NodeList playlistChild = playlistElement.getElementsByTagName("dict");

			for (int i = 0; i < playlistChild.getLength(); i++) {
				Playlist p = new Playlist();

				NodeList playlistElements = playlistChild.item(i).getChildNodes();
				for (int j = 0; j < playlistElements.getLength(); j++) {
					if(playlistElements.item(j).getTextContent().equals("Playlist ID")){
						//System.out.println(playlistElements.item(j + 1).getTextContent());
						int playlistId = Integer.parseInt(playlistElements.item(j + 1).getTextContent());
						p.setPlaylistId(playlistId);
						//System.out.println("playlist id" + playlistId);
					}
					if(playlistElements.item(j).getTextContent().equals("Playlist Persistent ID")){
						String playlistPId = playlistElements.item(j + 1).getTextContent();
						p.setplaylistPersistentId(playlistPId);	
						//System.out.println("playlist persistent id" + playlistPId);
					}
					if(playlistElements.item(j).getTextContent().equals("Name")){
						String playlistName = playlistElements.item(j + 1).getTextContent();
						p.setPlaylistName(playlistName);		
						//System.out.println("playlist name" + playlistName);
					}
					if(playlistElements.item(j).getTextContent().equals("Track ID")){
						//new collection of tracks
						Collection<Track> playlistTracks = new ArrayList<Track>();
						String trackID = playlistElements.item(j+1).getTextContent();
						int id = Integer.parseInt(trackID);
						//System.out.println(trackID);
						for (Track track : tracks) {
							//if id is a track already saved
							if(track.getTrackId() == id){
								//System.out.println("yes");
								playlistTracks.add(new Track(id, track.getTrackName(), track.getArtist(), track.getAlbum(), track.getGenre(), track.getTrackNumber(), track.gettrackPersistentId(), user.getLibraryPersistentId()));
								//add collection to playlist
								p.setTracks(playlistTracks);
								
								}
							
							}
						playlistDAO.batchInsertPlaylists(playlists);	
					}
					if(p.getPlaylistName() != null){
						playlists.add(p);
						//System.out.println("added playlists");
					}

					
					}

			}
			
		
				if(user.getLibraryPersistentId() != null){

					//user.setTracks(tracks);
					//user.setPlaylists(playlists);
					userDAO.insertUser(user);
					System.out.println("done");
				}
			

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
