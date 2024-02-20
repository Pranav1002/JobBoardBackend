package com.project.controllers;

import com.project.models.CompanySocialNetwork;
import com.project.services.CompanySocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/company/socialnetwork")
public class CompanySocialNetworkController {

    @Autowired
    private CompanySocialNetworkService companySocialNetworkService;

    @PostMapping("/add/{companyId}")
    public void addSocialNetwork(@RequestBody CompanySocialNetwork sn, @PathVariable Integer companyId) {
        companySocialNetworkService.addSocialNetwork(sn, companyId);
    }

    @GetMapping("/get/{companyId}")
    public ResponseEntity<CompanySocialNetwork> getSocialNetwork(@PathVariable Integer companyId) {
        CompanySocialNetwork companySocialNetwork =  companySocialNetworkService.getSocialNetworkById(companyId);
        return ResponseEntity.ok(companySocialNetwork);
    }

    @PutMapping("/update/{companyId}")
    public void updateSocialNetwork(@RequestBody CompanySocialNetwork sn, @PathVariable Integer companyId) {
        companySocialNetworkService.updateSocialNetwork(sn, companyId);
    }
}