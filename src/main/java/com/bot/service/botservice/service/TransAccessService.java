package com.bot.service.botservice.service;

import com.bot.service.botservice.model.*;
import com.bot.service.botservice.utils.ParameterStringBuilder;
import com.bot.service.botservice.utils.util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransAccessService {

    @Value("${transaccess.username}")
    private String userName;

    @Value("${transaccess.password}")
    private String password;

    @Value("${transaccess.Authorization}")
    private String authorization;

    @Value("${transaccess.endpoint}")
    private String endpoint;

    public DocumentTypeList getDocumentTypeList(String accessToken) {
        String URLAddress = endpoint + "/rest/getDropDownData?dropDownType=DocumentType&access_token=" + accessToken;
        System.out.println("URLAddress::::::::::::::" + URLAddress);
        String output;
        DocumentTypeList documentTypeList = new DocumentTypeList();
        try {
            URL url = new URL(URLAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((output = br.readLine()) != null) {
                Gson gson = new Gson();
                documentTypeList = gson.fromJson(output, DocumentTypeList.class);
            }
            System.out.println("propertyList:::::::::::::" + documentTypeList);
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return documentTypeList;

    }

    public PropertyTypeList getPropertyList(String accessToken) {
        String URLAddress = endpoint + "/rest/getDropDownData?dropDownType=PropertyType&access_token=" + accessToken;
        System.out.println("URLAddress::::::::::::::" + URLAddress);
        String output;
        PropertyTypeList propertyList = new PropertyTypeList();
        try {
            URL url = new URL(URLAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((output = br.readLine()) != null) {
                Gson gson = new Gson();
                propertyList = gson.fromJson(output, PropertyTypeList.class);
            }
            System.out.println("propertyList:::::::::::::" + propertyList);
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return propertyList;
    }


    public IndexTypeList getIndexTypeList(String acessToken) {
        String URLAddress = endpoint + "/rest/getDropDownData?dropDownType=indexType&access_token=" + acessToken;
        System.out.println("URLAddress::::::::::::::" + URLAddress);
        String output;
        IndexTypeList indexResponse = new IndexTypeList();
        try {

            URL url = new URL(URLAddress);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((output = br.readLine()) != null) {
                System.out.println("Output:::::::::::::" + output);
                Gson gson = new Gson();
                indexResponse = gson.fromJson(output, IndexTypeList.class);
                System.out.println("indexResponse:::::::::::::" + indexResponse);
            }
            System.out.println("indexResponse:::::::::::::" + indexResponse);
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

        return indexResponse;


    }

    public String uploadDocumet(UploadModel uploadModel) throws IOException {
        String URLAddress = endpoint + "rest/addDocuments?access_token=" + getAccessToken();
        HttpResponse response;
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost = new HttpPost(URLAddress);
        MultipartEntityBuilder entity = MultipartEntityBuilder.create();
        entity.addPart("propertyID", new StringBody(uploadModel.getPropertyID(), ContentType.DEFAULT_TEXT));
        entity.addPart("pdfConvertableValue", new StringBody(uploadModel.getPdfConvertable(), ContentType.DEFAULT_TEXT));
        entity.addPart("indexValue", new StringBody(uploadModel.getIndexValue(), ContentType.DEFAULT_TEXT));
        entity.addPart("indexType", new StringBody(String.valueOf(uploadModel.getIndexType()), ContentType.DEFAULT_TEXT));
        entity.addPart("documentType", new StringBody(String.valueOf(uploadModel.getDocumentType()), ContentType.DEFAULT_TEXT));
        entity.addPart("folderNames", new StringBody(String.valueOf(uploadModel.getFolderName()), ContentType.DEFAULT_TEXT));
        File f = util.convertToFile(uploadModel.getDocfile());
        entity.addPart("documents", new InputStreamBody(new FileInputStream(f), f.getName()));
        //httppost.setEntity(entity);
        HttpEntity responseEntity = entity.build();
        System.out.println("responseEntity::::::::::::::::" + responseEntity);
        httppost.setEntity(responseEntity);
        response = httpclient.execute(httppost);
        System.out.println("response:::::" + response);
        return util.readAll(new InputStreamReader(response.getEntity().getContent()));

    }

    public String getAccessToken() {
        OauthResponse oauthResponse;
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", userName);
        parameters.put("password", password);
        parameters.put("accessUser", userName);
        String URLAddress = endpoint + "oauth/token?grant_type=password";
        try {
            URL obj = new URL(URLAddress);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Authorization", "Basic cmVzdGFwcDpyZXN0YXBw");
            // Output the results
            OutputStream output = con.getOutputStream();
            output.write(ParameterStringBuilder.getParamsString(parameters).getBytes());
            output.flush();
            System.out.println("67:::::::::::::::::::::::");
            if (con.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                Gson gson = new Gson();
                JsonParser jsonParser = new JsonParser();
                JsonObject object = (JsonObject) jsonParser.parse(response.toString());
                oauthResponse = gson.fromJson(object, OauthResponse.class);
                in.close();
                System.out.println("oauthResponse.getAccess_token:::::::::::" + oauthResponse.getAccess_token());
                return oauthResponse.getAccess_token();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JsonObject addDocument(UploadModel model) throws IOException {
        String URLAddress = endpoint + "rest/addDocuments?access_token=" + getAccessToken();
        File file = util.convertToFile(model.getDocfile());
        MultipartEntityBuilder multipartEntityBulider = MultipartEntityBuilder.create();
        multipartEntityBulider.addPart("propertyID", new StringBody(model.getPropertyID(),
                ContentType.DEFAULT_TEXT));
        multipartEntityBulider.addPart("pdfConvertableValue", new
                StringBody(model.getPdfConvertable(), ContentType.DEFAULT_TEXT));
        multipartEntityBulider.addPart("indexValue", new StringBody(model.getIndexValue(),
                ContentType.DEFAULT_TEXT));
        multipartEntityBulider.addPart("indexType", new
                StringBody(String.valueOf(model.getIndexType()), ContentType.DEFAULT_TEXT));
        multipartEntityBulider.addPart("documentType", new
                StringBody(String.valueOf(model.getDocumentType()), ContentType.DEFAULT_TEXT));
        multipartEntityBulider.addPart("folderNames", new
                StringBody(String.valueOf(model.getFolderName()), ContentType.DEFAULT_TEXT));
        multipartEntityBulider.addPart("documents", new FileBody(file));


        HttpURLConnection con = (HttpURLConnection) new URL(URLAddress).openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "multipart/form-data; charset=utf-8");
        con.setRequestProperty("Authorization", authorization);
        OutputStream output = con.getOutputStream();
        output.write(multipartEntityBulider.toString().getBytes());
        output.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response.toString());
        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(response.toString());
        in.close();
        return object;
    }

}
