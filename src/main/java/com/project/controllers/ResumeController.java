package com.project.controllers;

import com.project.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload/{jsId}")
    public ResponseEntity<?> uploadResume(@RequestParam("resume") MultipartFile file, @PathVariable Integer jsId) throws IOException {
        String uploadResume = resumeService.uploadResume(file, jsId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadResume);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadResume(@PathVariable String fileName) throws IOException {
        byte[] imageData=resumeService.downloadResume(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(imageData);

    }

}
