package dss.project.services.ejb;

import java.io.File;
import java.io.InputStream;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import dss.project.dao.PlaylistDAO;
import dss.project.dao.TrackDAO;
import dss.project.dao.UserDAO;
import dss.project.services.DataImportService;

@Stateless
@Local
@Path("/import")
public class DataImportServiceEJB implements DataImportService {

	@Inject
	private PlaylistDAO playlistDAO;
	@Inject 
	private TrackDAO trackDAO;
	@Inject
	private UserDAO userDAO;
	
		
	public void importXML(Document dom) {

		
		  try {
			  
				dom = (Document) new File("C:\\Users\\Siobhan\\Desktop\\ItunesMusicLibrary1.xml");
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();

				Document doc = dBuilder.parse((InputStream) dom);

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				if (doc.hasChildNodes()) {
					 
					printNote(doc.getChildNodes());
			 
				}
			 
		  
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			  }
			 
	private static void printNote(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			 
			Node tempNode = nodeList.item(count);
		 
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
		 
				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());
		 
				if (tempNode.hasAttributes()) {
		 
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
		 
					for (int i = 0; i < nodeMap.getLength(); i++) {
		 
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());
		 
					}
		 
				}
		 
				if (tempNode.hasChildNodes()) {
		 
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());
					
				}
		 
				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
		 

				
				 
			}
		 
		    }
		
		  
		 
	}

	


}
