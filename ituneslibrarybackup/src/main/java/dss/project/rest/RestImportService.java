package dss.project.rest;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.parsers.ParserConfigurationException;







import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.xml.sax.SAXException;

import dss.project.services.DataImportService;

@Path("/register")
public class RestImportService {
	
	@EJB
	DataImportService importService;

	@POST
	@Consumes("multipart/form-data")
	public String importUploadedFile(@MultipartForm FileUploadForm form) throws SAXException, ParserConfigurationException {
		String result = "";
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(form.getFileData());

			File newFile = new File("tempData");
			String filepath = newFile.getAbsolutePath();
			
			Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
			int data;
			while ((data = byteArrayInputStream.read()) != -1) {
				output.write(data);
			}
			output.close();

			if (importService == null)
				System.out.println("no filepath");
			importService.importXML(filepath);
		}
		catch (IOException e) {
			result = "Import did not complete";
			e.printStackTrace();
		}
		return result;
	}

}
