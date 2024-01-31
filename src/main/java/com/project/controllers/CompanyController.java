package com.project.controllers;

import com.project.payloads.CompanyDto;
import com.project.payloads.JobDto;
import com.project.services.CompanyService;
import com.project.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobService jobService;


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

    @PutMapping("/update/{companyId}")
    public boolean updateCompanyById(@RequestBody CompanyDto companyDto, @PathVariable Integer companyId)
    {
        return this.companyService.updateCompany(companyDto, companyId);
    }

    @DeleteMapping("/delete/{companyId}")
    public boolean deleteCompanyById(@PathVariable Integer companyId)
    {
        return this.companyService.deleteCompany(companyId);
    }

}
