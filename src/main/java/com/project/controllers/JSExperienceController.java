package com.project.controllers;

import com.project.models.JSExperience;
import com.project.services.JSExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/jobseeker/experience")
public class JSExperienceController {

    @Autowired
    private JSExperienceService jsExperienceService;

    @PostMapping("/add/{jsId}")
    public void addExperience(@RequestBody JSExperience jsExperience, @PathVariable Integer jsId) {
        this.jsExperienceService.addExperience(jsExperience, jsId);
    }

    @GetMapping("/get/{jsId}")
    public ResponseEntity<List<JSExperience>> getExperienceById(@PathVariable Integer jsId) {
        return ResponseEntity.ok(this.jsExperienceService.getExperienceById(jsId));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<JSExperience>> getExperiences() {
        return ResponseEntity.ok(this.jsExperienceService.getExperiences());
    }

    @PutMapping("update")
    public ResponseEntity<JSExperience> updateExperience(@RequestBody JSExperience jsExperience) {
        return ResponseEntity.ok(this.jsExperienceService.updateExperience(jsExperience));
    }

    @DeleteMapping("delete/all/{jsId}")
    public void deleteAllExperience(@PathVariable Integer jsId) {
        this.jsExperienceService.deleteAllExperience(jsId);
    }

    @DeleteMapping("delete/exp/{expId}")
    public void deleteOneExperience(@PathVariable Integer expId)
    {
        this.jsExperienceService.deleteOneExperience(expId);
    }

}

