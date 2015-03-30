package dss.project.services;


import javax.ejb.Local;

import org.w3c.dom.Document;

@Local
public interface DataImportService {

	public void importXML(Document dom);
}
