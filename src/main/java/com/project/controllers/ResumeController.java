package com.project.controllers;

import com.project.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/jobseeker/resume/upload/{jsId}")
    public ResponseEntity<Map> uploadResume(@RequestParam("resume") MultipartFile file, @PathVariable Integer jsId) throws IOException {
        Map data=this.resumeService.uploadResume(file, jsId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("jobseeker/resume/download/{jsId}")
    public ResponseEntity<?> downloadResume(@PathVariable Integer jsId) throws IOException {
        byte[] imageData=resumeService.downloadResume(jsId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(imageData);

    }

}
