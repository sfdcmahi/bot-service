package com.bot.service.botservice.model;

public class DocumentResponse {
	private int documentTypeId;
	private String documentType;
	private int companyId;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	

}
