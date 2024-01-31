package com.project.controllers;

import com.project.payloads.JobSeekerDto;
import com.project.services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/jobseeker")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @GetMapping("/{jsId}")
    public ResponseEntity<JobSeekerDto> getJobSeeker(@PathVariable Integer jsId)
    {
        return ResponseEntity.ok(this.jobSeekerService.getJobSeekerById(jsId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobSeekerDto>> getAllJobSeekers()
    {
        return ResponseEntity.ok(jobSeekerService.getAllJobSeekers());
    }

    @PostMapping("/addJobseeker")
    public void addJobSeeker(@RequestBody JobSeekerDto jobSeekerDto)
    {
        this.jobSeekerService.addJobSeeker(jobSeekerDto);
    }

    @PutMapping("update/{jsId}")
    public ResponseEntity<JobSeekerDto> updateJobSeeker(@RequestBody JobSeekerDto jobSeekerDto, @PathVariable Integer jsId)
    {
        JobSeekerDto updatedJobSeeker = this.jobSeekerService.updateJobSeeker(jobSeekerDto, jsId);

        return ResponseEntity.ok(updatedJobSeeker);
    }

    @DeleteMapping("/delete/{jsId}")
    public void deleteJobSeeker(@PathVariable Integer jsId)
    {
        this.jobSeekerService.deleteJobSeeker(jsId);
    }

}
