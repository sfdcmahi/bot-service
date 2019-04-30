package com.bot.service.botservice.model;

import java.io.File;
import java.io.FileInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

public class UploadFile {
	public static void main(String[] args) {
		File inFile = new File("C:\\Users\\yitsol\\Desktop\\TransAccess\\Docs\\DataSets_ORCF.xlsx");
		FileInputStream fis;
		try {
		fis = new FileInputStream(inFile);
		DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://52.61.138.118:8080/TransAccessImagingWSORCFDMS/rest/addDocuments?access_token=13dba3aa-3117-48d3-b01e-8b5df6ff6b3f");
		MultipartEntityBuilder  entity = MultipartEntityBuilder.create();    
		entity.addPart("propertyID", new StringBody("demo999",ContentType.DEFAULT_TEXT));
		entity.addPart("pdfConvertableValue",new StringBody("false", ContentType.DEFAULT_TEXT));
		entity.addPart("indexValue",  new StringBody("789-78978991",ContentType.DEFAULT_TEXT));
		entity.addPart("indexType", new StringBody(String.valueOf("1"), ContentType.DEFAULT_TEXT));
		entity.addPart("documentType",  new StringBody(String.valueOf("2082"), ContentType.DEFAULT_TEXT));
		entity.addPart("folderNames", new StringBody(String.valueOf("Production/NON-CONSTRUCTION EXECUTION CLOSING/Section 1"), ContentType.DEFAULT_TEXT));
		entity.addPart("documents", new InputStreamBody(fis, inFile.getName()));
		//httppost.setEntity(entity);
		HttpEntity responseEntity =entity.build();
		System.out.println("responseEntity::::::::::::::::"+responseEntity);
		httppost.setEntity(responseEntity);
		HttpResponse response = httpclient.execute(httppost);
		System.out.println("response:::::"+response);
		int statusCode = response.getStatusLine().getStatusCode();
		           String responseString = EntityUtils.toString(responseEntity, "UTF-8");
		System.out.println("[" + statusCode + "] " + responseString);
		}catch(Exception e) {
		e.printStackTrace();
		}
	}
	

}
