package com.bot.service.botservice.service;

import com.bot.service.botservice.model.OauthResponse;
import com.bot.service.botservice.model.UploadModel;
import com.bot.service.botservice.utils.ParameterStringBuilder;
import com.bot.service.botservice.utils.util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


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
        String URLAddress = endpoint + "rest/addDocumentsUI?access_token=" + getAccessToken();
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
