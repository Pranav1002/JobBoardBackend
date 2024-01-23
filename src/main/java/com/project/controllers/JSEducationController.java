package com.project.controllers;

import com.project.models.JSEducation;
import com.project.services.JSEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobseeker/education")
public class JSEducationController {

    @Autowired
    private JSEducationService jsEducationService;

    @PostMapping("/add/{jsId}")
    public boolean addEducation(@RequestBody JSEducation jsEducation, @PathVariable Integer jsId)
    {
        this.jsEducationService.addEducation(jsEducation, jsId);
        return true;
    }

    @GetMapping("/get/{jsId}")
    public ResponseEntity<List<JSEducation>> getEducationById(@PathVariable Integer jsId)
    {
        return ResponseEntity.ok(this.jsEducationService.getEducationById(jsId));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<JSEducation>> getEducations()
    {
        return ResponseEntity.ok(this.jsEducationService.getEducations());
    }

    @PutMapping("update")
    public ResponseEntity<JSEducation> updateEducation(@RequestBody JSEducation jsEducation)
    {
        return ResponseEntity.ok(this.jsEducationService.updateEducation(jsEducation));
    }

    @DeleteMapping("delete/all/{jsId}")
    public boolean deleteAllEducation(@PathVariable Integer jsId)
    {
        this.jsEducationService.deleteAllEducation(jsId);
        return true;
    }

    @DeleteMapping("delete/edu/{eduId}")
    public boolean deleteOneEducation(@PathVariable Integer eduId)
    {
        this.jsEducationService.deleteEducation(eduId);
        return true;
    }

}
