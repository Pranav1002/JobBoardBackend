package com.project.controllers;

import com.project.services.JobSeekerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/jobseeker/image")
public class JobSeekerImageController {

    @Autowired
    private JobSeekerImageService jobSeekerImageService;


    @PostMapping("/upload/{userId}")
    public ResponseEntity<Map> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer userId) throws IOException {
        Map data=this.jobSeekerImageService.uploadImage(file, userId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }




    @GetMapping("/download/{jsId}")
    public ResponseEntity<String> downloadImage(@PathVariable Integer jsId) throws IOException {
        String imageData = jobSeekerImageService.downloadImage(jsId);
        return new ResponseEntity<>(imageData, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> changeImage(@RequestParam("image") MultipartFile file, @PathVariable Integer userId) throws IOException {
        String changedImage = jobSeekerImageService.changeImage(file, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(changedImage);
    }

}
