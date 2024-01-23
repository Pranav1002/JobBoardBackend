package com.project.services.impl;

import com.project.Repositories.JSExperienceRepository;
import com.project.Repositories.JobSeekerRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.JSEducation;
import com.project.models.JSExperience;
import com.project.models.JobSeeker;
import com.project.services.JSExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JSExperienceServiceImpl implements JSExperienceService {
    @Autowired
    private JSExperienceRepository jsExperienceRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public void addExperience(JSExperience experience, Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", "Id", jsId));

        experience.setJobSeeker(jobSeeker);

        this.jsExperienceRepository.save(experience);
    }

    @Override
    public List<JSExperience> getExperienceById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", "Id", jsId));

        List<JSExperience> experiences = this.jsExperienceRepository.findByJobSeeker(jobSeeker).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", "Id", jsId));

        return experiences;
    }

    @Override
    public List<JSExperience> getExperiences() {
        return this.jsExperienceRepository.findAll();
    }

    @Override
    public JSExperience updateExperience(JSExperience experience) {
        Integer expId = experience.getExpId();

        if (!jsExperienceRepository.existsById(expId)) {
            return null;
        }

        JSExperience experience1 = this.jsExperienceRepository.findByExpId(expId).orElseThrow(() -> new ResourceNotFoundException("JSExperience", " Id ", expId));

        experience1.setJobRole(experience.getJobRole());
        experience1.setOrganization(experience.getOrganization());
        experience1.setDescription(experience.getDescription());
        experience1.setStartYear(experience.getStartYear());
        experience1.setEndYear(experience.getEndYear());

        return this.jsExperienceRepository.save(experience1);
    }

    @Override
    public void deleteAllExperience(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", "Id", jsId));

        List<JSExperience> experiences = this.jsExperienceRepository.findByJobSeeker(jobSeeker).orElseThrow(()->new ResourceNotFoundException("JobSeeker","Id",jsId));

        this.jsExperienceRepository.deleteAll(experiences);
    }

    @Override
    public void deleteOneExperience(Integer expId) {
        JSExperience experience = this.jsExperienceRepository.findByExpId(expId).orElseThrow(() -> new ResourceNotFoundException("JSExperience", " Id ", expId));

        this.jsExperienceRepository.delete(experience);
    }
}
