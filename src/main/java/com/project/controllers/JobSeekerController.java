package com.project.controllers;

import com.project.payloads.CompanyDto;
import com.project.payloads.JobSeekerAddressDto;
import com.project.payloads.JobSeekerDto;
import com.project.services.AuthenticationService;
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

    @Autowired
    private AuthenticationService authenticationService;

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

    @PutMapping("update/{userId}")
    public ResponseEntity<JobSeekerDto> updateJobSeeker(@RequestBody JobSeekerDto jobSeekerDto, @PathVariable Integer userId)
    {
        JobSeekerDto updatedJobSeeker = this.jobSeekerService.updateJobSeeker(jobSeekerDto, userId);

        return ResponseEntity.ok(updatedJobSeeker);
    }

    @DeleteMapping("/delete/{jsId}")
    public void deleteJobSeeker(@PathVariable Integer jsId)
    {
        this.jobSeekerService.deleteJobSeeker(jsId);
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<JobSeekerDto> getJobSeekerByUser(@PathVariable Integer userId)
    {
        JobSeekerDto jobSeekerDto = this.jobSeekerService.getJobSeekerByUserId(userId);

        return ResponseEntity.ok(jobSeekerDto);
    }

    @PutMapping("update/address/{jsId}")
    public boolean updateJobSeekerAdress(@RequestBody JobSeekerAddressDto jobSeekerAddressDto, @PathVariable Integer jsId)
    {
        return this.jobSeekerService.updateJobSeekerAddress(jsId, jobSeekerAddressDto);
    }

    @GetMapping("get/address/{jsId}")
    public ResponseEntity<JobSeekerAddressDto> getJobSeekerAddress(@PathVariable Integer jsId)
    {
        JobSeekerAddressDto jobSeekerAddressDto = this.jobSeekerService.getJobSeekerAddress(jsId);
        return ResponseEntity.ok(jobSeekerAddressDto);
    }

    @GetMapping("get/companies")
    public ResponseEntity<List<CompanyDto>> getCompanies(){
        List<CompanyDto> companyDtos = jobSeekerService.getAllCompanies();
        return ResponseEntity.ok(companyDtos);
    }

    @GetMapping("get/company/{companyId}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Integer companyId)
    {
        return ResponseEntity.ok(jobSeekerService.getCompanyById(companyId));
    }

    @PutMapping("change-password/{userId}")
    public ResponseEntity<Boolean> changePassword(@PathVariable Integer userId, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword){
        return ResponseEntity.ok(authenticationService.changePassword(userId, oldPassword, newPassword, confirmPassword));
    }

}
