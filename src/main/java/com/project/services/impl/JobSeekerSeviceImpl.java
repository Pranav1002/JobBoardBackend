package com.project.services.impl;

import com.project.Repositories.JobSeekerRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.JobSeeker;
import com.project.payloads.JobSeekerDto;
import com.project.services.JobSeekerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSeekerSeviceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobSeekerDto getJobSeekerById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));
        return this.modelMapper.map(jobSeeker, JobSeekerDto.class);
    }



    @Override
    public JobSeekerDto updateJobSeeker(JobSeekerDto jobSeeker) {
        return null;
    }

    @Override
    public void deleteJobSeeker(Integer jsId) {

    }

    @Override
    public void addJobSeeker(JobSeekerDto jobSeekerDto) {

        JobSeeker jobSeeker = this.modelMapper.map(jobSeekerDto, JobSeeker.class);
        JobSeeker savedJobSeeker = this.jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public List<JobSeekerDto> getAllJobSeekers() {
        List<JobSeeker> jobSeekers= this.jobSeekerRepository.findAll();
        List<JobSeekerDto> jobSeekerDtos = jobSeekers.stream().map(jobSeeker -> this.modelMapper.map(jobSeeker, JobSeekerDto.class)).collect(Collectors.toList());

        return jobSeekerDtos;
    }
}
