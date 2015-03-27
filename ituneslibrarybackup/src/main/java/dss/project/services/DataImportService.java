package dss.project.services;


import java.io.File;

import javax.ejb.Local;

@Local
public interface DataImportService {

	public void importXML(File xmlFile);
}
