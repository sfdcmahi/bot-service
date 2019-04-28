package com.bot.service.botservice.model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

//https://www.mkyong.com/spring-boot/spring-boot-file-upload-example-ajax-and-rest/
public class UploadModel {

    private String pdfConvertable;
    private String indexValue;
    private String indexType;
    private String documentType;
    private String folderName;
    private String propertyID;
    private MultipartFile docfile;

    public void setPdfConvertable(String pdfConvertable){
        this.pdfConvertable=pdfConvertable;
    }
    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;

    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;

    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;

    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;

    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;

    }


    public void setDocfile(MultipartFile f) {
        this.docfile = f;
    }

    public MultipartFile getDocfile() {
        return this.docfile;
    }


    public String getPdfConvertable() {
        return this.pdfConvertable;
    }

    public String getIndexValue() {
        return this.indexValue;
    }

    public String getIndexType() {
        return this.indexType;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public String getPropertyID() {
        return this.propertyID;
    }
}