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
    private CompanyImageService companyImageService;

    @PostMapping("/upload/{companyId}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer companyId) throws IOException {
        String uploadImage = companyImageService.uploadImage(file, companyId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = companyImageService.downloadImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<?> changeImage(@RequestParam("image") MultipartFile file, @PathVariable Integer companyId) throws IOException {
        String changedImage = companyImageService.changeImage(file, companyId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(changedImage);
    }

}
