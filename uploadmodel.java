package com.bot.service.botservice.model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

//https://www.mkyong.com/spring-boot/spring-boot-file-upload-example-ajax-and-rest/
public class UploadModel {
    File file;
    private String pdfConvertable;
    private String indexValue;
    private String indexType;
    private String documentType;
    private String folderName;
    private String propertyID;
    public class UploadModelBuilder{
        File file;
        private String pdfConvertable;
        private String indexValue;
        private String indexType;
        private String documentType;
        private String folderName;
        private String propertyID;

        public UploadModelBuilder setFile(File file){
            this.file=file;
            return this;
        }

        public UploadModelBuilder setPdfConvertable(String pdfConvertable){
            this.pdfConvertable=pdfConvertable;
            return this;
        }

        public UploadModelBuilder setIndexValue(String indexValue){
            this.indexValue=indexValue;
            return this;
        }

        public UploadModelBuilder setIndexType(String indexType){
            this.indexType=indexType;
            return this;
        }

        public UploadModelBuilder setDocumentType(String documentType){
            this.documentType=documentType;
            return this;
        }

        public UploadModelBuilder setFolderName(String folderName){
            this.folderName=folderName;
            return this;
        }

        public UploadModelBuilder setPropertyID(String propertyID){
            this.propertyID=propertyID;
            return this;
        }
        public UploadModel build(){
            return new UploadModel(this);
        }
    }
    private UploadModel(UploadModelBuilder ub){
        this.file=ub.file;
        this.pdfConvertable=ub.pdfConvertable;
        this.indexValue=ub.indexValue;
        this.indexType=ub.indexType;
        this.documentType=ub.documentType;
        this.folderName=ub.folderName;
        this.propertyID=ub.propertyID;
    }

    public File getFile(){
        return this.file;
    }

    public String getPdfConvertable(){
        return this.pdfConvertable;
    }

    public String getIndexValue(){
        return this.indexValue;
    }

    public String getIndexType(){
        return this.indexType;
    }

    public String getDocumentType(){
        return this.documentType;
    }

    public String getFolderName(){
        return this.folderName;
    }
    public String getPropertyID(){
        return this.propertyID;
    }

}
