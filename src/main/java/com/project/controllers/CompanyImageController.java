package com.project.controllers;

import com.project.services.CompanyImageService;
import com.project.services.JobSeekerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/company/image")
public class CompanyImageController {

    @Autowired
    private JobSeekerImageService jobSeekerImageService;

    @PostMapping("/upload/{jobSeekerId}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer jobSeekerId) throws IOException {
        String uploadImage = jobSeekerImageService.uploadImage(file, jobSeekerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = jobSeekerImageService.downloadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

    @PutMapping("/update/{jobSeekerId}")
    public ResponseEntity<?> changeImage(@RequestParam("image") MultipartFile file, @PathVariable Integer jobSeekerId) throws IOException {
        String changedImage = jobSeekerImageService.changeImage(file, jobSeekerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(changedImage);
    }

}
