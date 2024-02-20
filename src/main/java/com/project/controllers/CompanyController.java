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

}
