package com.bot.service.botservice.controller;

import com.bot.service.botservice.model.DocUpload;
import com.bot.service.botservice.model.UploadModel;
import com.bot.service.botservice.service.TransAccessService;
import com.google.gson.JsonObject;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bot/v1/")
public class UploadDocument {
    private final Logger logger = LoggerFactory.getLogger(UploadDocument.class);
    
    @Autowired
    DocUpload docupload;

    @GetMapping("/getaccesstoken")
    public ResponseEntity<?> getAccessToken(){
       System.out.println("getAccessToken:::::::::::::");
        return new ResponseEntity<String>(transAccessService.getAccessToken(), HttpStatus.OK);
    }
    
    
    @Autowired
    private TransAccessService transAccessService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute UploadModel upload) {

        logger.debug("Upload started..");
        JsonObject object;
        if (upload.getDocfile().isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        try {
             object=transAccessService.addDocument(upload);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity( object.toString(), new HttpHeaders(), HttpStatus.OK);
    }
    
    
    /*Kiran Yitsol Changes Start*/
    
    @GetMapping("/getaccesstoken1")
    public Map<String, Object> getAccessToken1(){
       Map<String, Object> mapResponse=new HashMap<String, Object>();
       String accessToken=transAccessService.getAccessToken();
       mapResponse.put("key", "200");
       mapResponse.put("status", "success");
       mapResponse.put("access_token", accessToken);
       return mapResponse;
    }
    
    @RequestMapping("/getDropDownData")
    public Object getDropDownDocument(@RequestParam("dropDownType") String dropDownType,@RequestParam("access_token") String access_token){
    	if(dropDownType.equalsIgnoreCase("indexType")) {
    		return transAccessService.getIndexTypeList(access_token);
    	}else if(dropDownType.equalsIgnoreCase("DocumentType")) {
    		return transAccessService.getDocumentTypeList(access_token);
    	}else if(dropDownType.equalsIgnoreCase("PropertyType")) {
    		return transAccessService.getPropertyList(access_token);
    	}
    	return null;
    }
    
    @CrossOrigin
    @PostMapping(value ="/addDocuments")
	public Object addDocuments  (
			@RequestParam("propertyID") String propertyId,
			@RequestParam("documentType")String documentType,
			@RequestParam("indexType") String indexType,
			@RequestParam("indexValue") String indexValue,
			@RequestParam("documents") MultipartFile documents,@RequestParam("pdfConvertableValue") String pdfConvertableValue,@RequestParam("folderNames") String folderNames) throws IOException {
		
       	logger.info("Upload Document API  starts : ");
    	logger.info("Request Parameters :"
				+ "{\n\tpropertyId : " + propertyId + "\n\tdocumentType : " + documentType
				+ "\n\tindexType : " + indexType + "\n\tindexValue : "+indexValue + "\n\tpdfConvertableValue:"+pdfConvertableValue+"\n\tfolderNames:"+folderNames+"\n}");
    	UploadModel request = new UploadModel();
    	request.setPropertyID(propertyId);
    	request.setDocfile(documents);
    	request.setIndexType(indexType);
    	request.setIndexValue(indexValue);
    	request.setPdfConvertable(pdfConvertableValue);
    	request.setDocumentType(documentType);
    	request.setFolderName(folderNames);
    	request.setDocfile(documents);
    	String o=transAccessService.uploadDocumet(request);
        System.out.println(o);

        try{
            JSONParser parser = new JSONParser();
            return (JSONObject)  parser.parse(o);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
	}
    
}
