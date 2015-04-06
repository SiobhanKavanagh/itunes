package dss.project.rest;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {

	public FileUploadForm() {
	}

	private byte [] fileData;
	private String fileName;
	private String username;
	private String password;

	public String getFileName() {
		return fileName;
	}

	@FormParam("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte [] getFileData() {
		return fileData;
	}

	@FormParam("selectedFile")
	@PartType("application/octet-stream")
	public void setFileData(byte [] fileData) {
		this.fileData = fileData;
	}

	@FormParam("username")
	@PartType("text/plain")
	public void setUsername(String username){
		this.username = username;
	}
	
	@FormParam("password")
	@PartType("text/plain")
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
