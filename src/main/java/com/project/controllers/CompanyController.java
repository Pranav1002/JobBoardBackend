package com.project.controllers;

import com.project.payloads.CompanyAddressDto;
import com.project.payloads.CompanyDto;
import com.project.payloads.JobDto;
import com.project.payloads.JobSeekerDto;
import com.project.services.CompanyService;
import com.project.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @PostMapping("/add")
    public boolean addCompany(@RequestBody CompanyDto companyDto)
    {
        return this.companyService.addCompany(companyDto);
    }

    @GetMapping("/get/{companyId}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Integer companyId)
    {
        CompanyDto companyDto = this.companyService.getCompanyById(companyId);

        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CompanyDto> updateCompanyById(@RequestBody CompanyDto companyDto, @PathVariable Integer userId)
    {
        CompanyDto companyDto1 = this.companyService.updateCompany(companyDto, userId);
        return ResponseEntity.ok(companyDto1);
    }

    @DeleteMapping("/delete/{companyId}")
    public boolean deleteCompanyById(@PathVariable Integer companyId)
    {
        return this.companyService.deleteCompany(companyId);
    }

    @PutMapping("update/address/{companyId}")
    public boolean updateCompanyAddress(@PathVariable Integer companyId, @RequestBody CompanyAddressDto companyAddressDto)
    {
        return companyService.updateCompanyAddress(companyId ,companyAddressDto);
    }

    @GetMapping("get/address/{companyId}")
    public ResponseEntity<CompanyAddressDto> getCompanyAddress(@PathVariable Integer companyId)
    {
        CompanyAddressDto companyAddressDto = this.companyService.getCompanyAddress(companyId);
        return ResponseEntity.ok(companyAddressDto);
    }

    @GetMapping("get/jobseekers")
    public ResponseEntity<List<JobSeekerDto>> getAllJobSeekers()
    {
        return ResponseEntity.ok(companyService.getAllJobSeekers());
    }

    @GetMapping("get/applicants/{companyId}")
    public ResponseEntity<List<JobSeekerDto>> getApplicants(@PathVariable Integer companyId)
    {
        return ResponseEntity.ok(companyService.getApplicants(companyId));
    }

    @GetMapping("get/jobseeker/{jsId}")
    public ResponseEntity<JobSeekerDto> getJobSeekerById(@PathVariable Integer jsId){
        return ResponseEntity.ok(companyService.getJobSeekerById(jsId));
    }

}
