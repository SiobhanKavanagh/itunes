package dss.project.services;


import javax.ejb.Local;

@Local
public interface DataImportService {

	public void importXML(String file);
	
	public void initialiseUser(String username, String password);
}
