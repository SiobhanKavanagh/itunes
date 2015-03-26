package dss.project.services.ejb;

import java.io.File;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
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
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
			 
				doc.getDocumentElement().normalize();
					 
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
				NodeList nList = doc.getElementsByTagName("dict");
			 
				System.out.println("----------------------------");
			 
				for (int temp = 0; temp < nList.getLength(); temp++) {
			 
					Node nNode = nList.item(temp);
			 
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
			 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
			 
						System.out.println("dict id : " + eElement.getAttribute("id"));
						
					}
				}
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			  }
			 
	public static void main(String [] args){
		new DataImportServiceEJB();
	}


}
