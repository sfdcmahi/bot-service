package com.bot.service.botservice.controller;

import com.bot.service.botservice.model.CaseModel;
import com.bot.service.botservice.utils.ParameterStringBuilder;
import com.bot.service.botservice.utils.util;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bot/v1/case/")
public class CaseCreation {
    @CrossOrigin
    @PostMapping(value ="/create")
    public static Object CreateCase(@ModelAttribute CaseModel caseModel) throws IOException {
        /*MultipartEntityBuilder entity = MultipartEntityBuilder.create();
        entity.addPart("name", new StringBody(caseModel.getName(), ContentType.DEFAULT_TEXT));
        entity.addPart("description", new StringBody(caseModel.getDescription(), ContentType.DEFAULT_TEXT));
        entity.addPart("email", new StringBody(caseModel.getEmail(), ContentType.DEFAULT_TEXT));
        entity.addPart("subject", new StringBody(caseModel.getSubject(), ContentType.DEFAULT_TEXT));
        entity.addPart("phone", new StringBody(caseModel.getPhone(), ContentType.DEFAULT_TEXT));

        HttpResponse response;
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost post=new HttpPost("https://webto.salesforce.com/servlet/servlet.WebToCase?encoding=UTF-8");
        HttpEntity responseEntity = entity.build();
        post.setEntity(responseEntity);
        response = httpclient.execute(post);
*/

        Map<String, String> params=new HashMap();
        params.put("orgid","00D0K0000024n7e");
        params.put("name",caseModel.getName());
        params.put("email",caseModel.getEmail());
        params.put("phone",caseModel.getPhone());
        params.put("subject",caseModel.getSubject());
        params.put("description",caseModel.getDescription());

        String urlParameters = ParameterStringBuilder.getParamsString(params);
        System.out.println(urlParameters);
        String request = "https://webto.salesforce.com/servlet/servlet.WebToCase?encoding=UTF-8&"+urlParameters;
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            System.out.println(inputLine);
        }

        br.close();
        return "Done";
    }
}
