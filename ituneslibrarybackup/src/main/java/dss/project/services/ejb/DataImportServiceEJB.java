package dss.project.services.ejb;

import java.io.File;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import dss.project.services.DataImportService;

@Stateless
@Local
@Path("/import")
public class DataImportServiceEJB implements DataImportService {

	public DataImportServiceEJB(){
		importXML();
	}
	
	public void importXML() {
		
		  try {
			  
				File xmlFile = new File("C:\\Users\\Siobhan\\Desktop\\ItunesMusicLibrary1.xml");
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();

				Document doc = dBuilder.parse(xmlFile);

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

//	public static void main(String [] args){
//		new DataImportServiceEJB();
//	}


}
