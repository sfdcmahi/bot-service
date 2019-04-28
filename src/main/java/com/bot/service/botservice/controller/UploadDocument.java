package com.bot.service.botservice.controller;

import com.bot.service.botservice.model.UploadModel;
import com.bot.service.botservice.service.TransAccessService;
import com.google.gson.JsonObject;

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

@RestController
@RequestMapping("/bot/v1/")
public class UploadDocument {
    private final Logger logger = LoggerFactory.getLogger(UploadDocument.class);

    @GetMapping("/getaccesstoken")
    public ResponseEntity<?> getAccessToken(){

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
}
