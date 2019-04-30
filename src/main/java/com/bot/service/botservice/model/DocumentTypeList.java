package com.bot.service.botservice.model;

import java.util.Arrays;

public class DocumentTypeList {
	private DocumentResponse[] documentType;

	public DocumentResponse[] getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentResponse[] documentType) {
		this.documentType = documentType;
	}

	@Override
	public String toString() {
		return "DocumentTypeList [documentType=" + Arrays.toString(documentType) + "]";
	}
	

}
