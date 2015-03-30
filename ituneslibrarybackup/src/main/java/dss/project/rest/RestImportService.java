package dss.project.rest;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import dss.project.services.DataImportService;


@Path("/restImportService")
public class RestImportService {
	
	DocumentBuilder documentBuilder;
	Document dom;
	

	//@EJB
	//private UserService userServiceEJB;

	@Inject
	private DataImportService dataImport;

	@POST
	@Path("/import")
	@Consumes("multipart/form-data")
	public String importUploadedFile(@MultipartForm FileUploadForm form) throws SAXException, ParserConfigurationException {
		String resultString = "";
		try {
			File wb = File.createTempFile("xmlFile", ".xml");
			ByteArrayInputStream byteStreamFromUpload = new ByteArrayInputStream(form.getFileData());
			FileOutputStream fileOutputStreamFromByteStream = new FileOutputStream(wb);
			OutputStreamWriter encodedOutputStream = new OutputStreamWriter(fileOutputStreamFromByteStream, "UTF-8");
			Writer writerToCreatedFile = new BufferedWriter(encodedOutputStream);
			
			while (true){
				int byteStreamValue = byteStreamFromUpload.read();
				
				if(byteStreamValue != -1){
					writerToCreatedFile.write(byteStreamValue);
				}else{
					writerToCreatedFile.close();
					break;
				}
			}
			
			System.out.println("Temp file name:" +wb.getName());
			String path = wb.getCanonicalPath();
			
			
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			documentBuilder = dbf.newDocumentBuilder();
			dom = documentBuilder.parse(path);
			dataImport.importXML(dom);
			resultString = "Successful!!! :D";
					
			
		}
		catch (IOException e) {
			resultString = "Import was unsuccessful";
			e.printStackTrace();
		}
		return resultString;
	}

}
