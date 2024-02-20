package com.project.controllers;

import com.project.models.JobSeekerSocialNetwork;
import com.project.services.JobSeekerSocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jobseeker/socialnetwork")
public class JobSeekerSocialNetworkController {

    @Autowired
    private JobSeekerSocialNetworkService jobSeekerSocialNetworkService;

    @PostMapping("/add/{jsId}")
    public void addSocialNetwork(@RequestBody JobSeekerSocialNetwork sn, @PathVariable Integer jsId)
    {
        jobSeekerSocialNetworkService.addSocialNetwork(sn, jsId);
    }

    @GetMapping("/get/{jsId}")
    public ResponseEntity<JobSeekerSocialNetwork> getSocialNetwork(@PathVariable Integer jsId)
    {
        JobSeekerSocialNetwork jobSeekerSocialNetwork =  jobSeekerSocialNetworkService.getSocialNetworkById(jsId);
        return ResponseEntity.ok(jobSeekerSocialNetwork);
    }

    @PutMapping("/update/{jsId}")
    public void updateSocialNetwork(@RequestBody JobSeekerSocialNetwork sn, @PathVariable Integer jsId)
    {
        this.jobSeekerSocialNetworkService.updateSocialNetwork(sn, jsId);
    }

}
