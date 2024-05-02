package com.project.controllers;

import com.project.payloads.JobDto;
import com.project.payloads.JobSeekerDto;
import com.project.services.CompanyService;
import com.project.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/")
public class JobController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;

    @PostMapping("company/post/job/{companyId}")
    public boolean postJob(@RequestBody JobDto jobDto, @PathVariable Integer companyId)
    {
        return this.jobService.postJob(jobDto, companyId);
    }

    @GetMapping("company/get/jobs/{companyId}")
    public ResponseEntity<List<JobDto>> getJobByCompanyId(@PathVariable Integer companyId)
    {
        List<JobDto> jobDtos = this.jobService.getJobByCompanyId(companyId);

        return ResponseEntity.ok(jobDtos);
    }

    @PutMapping("company/update/job/{jobId}/{companyId}")
    public boolean updateJob(@RequestBody JobDto jobDto, @PathVariable Integer jobId, @PathVariable Integer companyId)
    {
        return this.jobService.updateJob(jobDto,jobId,companyId);
    }

    @DeleteMapping("company/delete/job/{jobId}/{companyId}")
    public boolean deleteJob(@PathVariable Integer jobId, @PathVariable Integer companyId)
    {
        return this.jobService.deleteJob(jobId,companyId);
    }

    @GetMapping("company/get/applicants/{jobId}")
    public ResponseEntity<Set<JobSeekerDto>> getJobApplicants(@PathVariable Integer jobId)
    {
        Set<JobSeekerDto> jobSeekerDtos = this.jobService.getJobApplicants(jobId);

        return ResponseEntity.ok(jobSeekerDtos);
    }

    @PostMapping("company/job/{jobId}/shortlist/{jsId}")
    public boolean shortlistApplicantByJob(@PathVariable Integer jobId, @PathVariable Integer jsId) {
        return this.jobService.shortlistApplicantByJob(jobId, jsId);
    }

    @GetMapping("company/get/shortlisted-applicants/{jobId}")
    public ResponseEntity<Set<JobSeekerDto>> getShortlistedApplicantsByJob(@PathVariable Integer jobId)
    {
        Set<JobSeekerDto> jobSeekerDtos = this.jobService.getShortlistedApplicantsByJob(jobId);

        return ResponseEntity.ok(jobSeekerDtos);
    }


    @GetMapping("/get/jobs")
    public ResponseEntity<List<JobDto>> getAllJobs()
    {
        List<JobDto> jobDtos =  this.jobService.getAllJobs();

        return ResponseEntity.ok(jobDtos);
    }

    @PostMapping("/jobseeker/{jsId}/apply/job/{jobId}")
    public boolean applyJob(@PathVariable Integer jsId, @PathVariable Integer jobId)
    {
        return this.jobService.applyJob(jsId, jobId);
    }

    @GetMapping("/jobseeker/{jsId}/get/applied-jobs")
    public ResponseEntity<Set<JobDto>> getAppliedJobs(@PathVariable Integer jsId)
    {
        Set<JobDto> jobDtos = this.jobService.getAppliedJobs(jsId);

        return ResponseEntity.ok(jobDtos);
    }

    @GetMapping("/jobseeker/{jsId}/get/shortlisted-jobs")
    public ResponseEntity<Set<JobDto>> getShortlistedJobs(@PathVariable Integer jsId)
    {
        Set<JobDto> jobDtos = this.jobService.getShortListedJobs(jsId);

        return ResponseEntity.ok(jobDtos);
    }

    @GetMapping("jobseeker/job/{jobId}")
    public ResponseEntity<JobDto> getJobByJobId(@PathVariable Integer jobId)
    {
        return ResponseEntity.ok(jobService.getJobByJobId(jobId));
    }

    @GetMapping("company/job/{jobId}")
    public ResponseEntity<JobDto> getJobByJobIdForCompany(@PathVariable Integer jobId)
    {
        return ResponseEntity.ok(jobService.getJobByJobId(jobId));
    }

}
