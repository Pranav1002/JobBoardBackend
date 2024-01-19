package com.project.controllers;

import com.project.payloads.JobSeekerDto;
import com.project.services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @GetMapping("/{jsId}")
    public ResponseEntity<JobSeekerDto> getJobSeeker(@PathVariable Integer jsId)
    {
        return ResponseEntity.ok(this.jobSeekerService.getJobSeekerById(jsId));
    }


}
