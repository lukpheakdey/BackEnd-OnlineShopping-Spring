package com.group.backend.demo.vendor.controller;

import com.group.backend.demo.shoppingcart.exception.FileUploadException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class UploadController {


    public static final String UPLOADED_FOLDER = "src/main/resources/static/";

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        System.out.println("controller called!"+ uploadfile.getOriginalFilename());

        if (uploadfile.isEmpty()) {

            return new ResponseEntity("please select a file!", HttpStatus.OK);

        }
        try {

            saveUploadedFiles(uploadfile);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    private void saveUploadedFiles(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
    }




    @RequestMapping(value = "/images/{img}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadFile(@PathVariable String img) {
        Path path = Paths.get(UPLOADED_FOLDER + img);
        InputStream in = null;
        try {
            in = new FileInputStream(new File(path.toUri()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
