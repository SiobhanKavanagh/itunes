package dss.project.rest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import dss.project.services.DataImportService;
import dss.project.services.UserService;


@Path("/restImportService")
public class RestImportService {

	@EJB
	private UserService userServiceEJB;

	@Inject
	private DataImportService dataImport;

	@POST
	@Path("/import")
	@Consumes("multipart/form-data")
	public String importUploadedFile(@MultipartForm FileUploadForm form) {
		String resultString = "";
		try {
			File wb = new File(new ByteArrayInputStream(form.getFileData()));
			dataImport.importSpreadsheet(wb);
			resultString = "Time taken: " + DataImportServiceEJB.duration + " milliseconds.";
			System.out.println(resultString);
		}
		catch (IOException e) {
			resultString = "Import was unsuccessful";
			e.printStackTrace();
		}
		return resultString;
	}

}
