package com.project.services.impl;

import com.project.Repositories.JSEducationRepository;
import com.project.Repositories.JobSeekerRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.JSEducation;
import com.project.models.JobSeeker;
import com.project.services.JSEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JSEducationServiceImpl implements JSEducationService {

    @Autowired
    private JSEducationRepository jsEducationRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public void addEducation(JSEducation education, Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        education.setJobSeeker(jobSeeker);

        this.jsEducationRepository.save(education);
    }

    @Override
    public List<JSEducation> getEducationById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        List<JSEducation> educations = this.jsEducationRepository.findByJobSeeker(jobSeeker).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        return educations;
    }

    @Override
    public List<JSEducation> getEducations() {
        return this.jsEducationRepository.findAll();
    }

    @Override
    public JSEducation updateEducation(JSEducation education) {

        Integer eduId = education.getEduId();

        if (!jsEducationRepository.existsById(eduId)) {
            return null;
        }

        JSEducation education1 = this.jsEducationRepository.findByEduId(eduId).orElseThrow(() -> new ResourceNotFoundException("JSEducation", " Id ", eduId));

        education1.setCourse(education.getCourse());
        education1.setDescription(education.getDescription());
        education1.setOrganization(education.getOrganization());
        education1.setStartYear(education.getStartYear());
        education1.setEndYear(education.getEndYear());

        return this.jsEducationRepository.save(education1);
    }

    @Override
    public void deleteAllEducation(Integer jsId) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        List<JSEducation> educations = this.jsEducationRepository.findByJobSeeker(jobSeeker).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        this.jsEducationRepository.deleteAll(educations);
    }

    @Override
    public void deleteEducation(Integer eduId) {

        JSEducation education = this.jsEducationRepository.findByEduId(eduId).orElseThrow(() -> new ResourceNotFoundException("JSEducation", " Id ", eduId));

        this.jsEducationRepository.delete(education);
    }
}
