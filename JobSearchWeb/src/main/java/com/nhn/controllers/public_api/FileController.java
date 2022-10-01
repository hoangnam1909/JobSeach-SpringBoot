package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.service.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/v1/upload")
public class FileController {

    @Autowired
    private StorageService service;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImageId = service.uploadImage(file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RespondObject("Successful", "image upload successful", uploadImageId));
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
