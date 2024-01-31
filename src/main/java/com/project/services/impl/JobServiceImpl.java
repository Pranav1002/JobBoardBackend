package com.project.services.impl;

import com.project.Repositories.CompanyRepository;
import com.project.Repositories.JobRepository;
import com.project.Repositories.JobSeekerRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.exceptions.UnauthorizedException;
import com.project.models.Company;
import com.project.models.Job;
import com.project.models.JobSeeker;
import com.project.payloads.JobDto;
import com.project.payloads.JobSeekerDto;
import com.project.services.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public boolean postJob(JobDto jobDto, Integer companyId) {

        Job job = this.modelMapper.map(jobDto, Job.class);

        Company company = this.companyRepository.findById(companyId).orElseThrow(()->new ResourceNotFoundException("Company","Id",companyId));

        job.setCompany(company);

        this.jobRepository.save(job);

        return true;
    }

    @Override
    public List<JobDto> getJobByCompanyId(Integer companyId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        List<Job> jobs = this.jobRepository.findByCompany(company).orElseThrow(()->new ResourceNotFoundException("Company","Id",companyId));

        List<JobDto> jobDtos = jobs.stream().map(job -> this.modelMapper.map(job, JobDto.class)).toList();

        return jobDtos;

    }

    @Override
    public boolean updateJob(JobDto jobDto, Integer jobId, Integer companyId) {

        Job job = this.jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","Id",jobId));

        if (!job.getCompany().getCompanyId().equals(companyId)) {
            throw new UnauthorizedException("Company does not have permission to update this job.");
        }

        job.setJobTitle(jobDto.getJobTitle());
        job.setJobDescription(jobDto.getJobDescription());
        job.setExperience(jobDto.getExperience());
        job.setSalary(jobDto.getSalary());
        job.setIndustry(jobDto.getIndustry());
        job.setQualification(jobDto.getQualification());
        job.setDeadline(jobDto.getDeadline());
        job.setCity(jobDto.getCity());
        job.setCountry(jobDto.getCountry());

        this.jobRepository.save(job);

        return true;
    }

    @Override
    public boolean deleteJob(Integer jobId, Integer companyId) {

        Job job = this.jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","Id",jobId));

        if (!job.getCompany().getCompanyId().equals(companyId)) {
            throw new UnauthorizedException("Company does not have permission to delete this job.");
        }

        this.jobRepository.delete(job);

        return true;
    }

    @Override
    public Set<JobSeekerDto> getJobApplicants(Integer jobId) {
        Job job = this.jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","Id",jobId));

        Set<JobSeeker> jobSeekers = job.getJobSeekers();

        Set<JobSeekerDto> jobSeekerDtos = jobSeekers.stream().map(jobSeeker -> this.modelMapper.map(jobSeeker, JobSeekerDto.class)).collect(Collectors.toSet());

        return jobSeekerDtos;
    }

    @Override
    public boolean shortlistApplicantByJob(Integer jobId, Integer jsId) {
        Job job = this.jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","Id",jobId));

        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        job.getShortlistedJobSeekers().add(jobSeeker);
        jobSeeker.getShortlistedJobs().add(job);

        this.jobRepository.save(job);
        this.jobSeekerRepository.save(jobSeeker);

        return true;
    }

    @Override
    public Set<JobSeekerDto> getShortlistedApplicantsByJob(Integer jobId) {
        Job job = this.jobRepository.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","Id",jobId));

        Set<JobSeeker> jobSeekers = job.getShortlistedJobSeekers();

        Set<JobSeekerDto> jobSeekerDtos = jobSeekers.stream().map(jobSeeker -> this.modelMapper.map(jobSeeker, JobSeekerDto.class)).collect(Collectors.toSet());

        return jobSeekerDtos;
    }

    @Override
    public List<JobDto> getAllJobs() {
        List<Job> jobs = this.jobRepository.findAll();

        List<JobDto> jobDtos = jobs.stream().map(job -> this.modelMapper.map(job, JobDto.class)).toList();

        return jobDtos;
    }


    //Job-JobSeeker apis.


    @Override
    public boolean applyJob(Integer jsId, Integer jobId)
    {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job", " Id ", jobId));

        jobSeeker.getAppliedJobs().add(job);

        job.getJobSeekers().add(jobSeeker);

        jobSeekerRepository.save(jobSeeker);

        jobRepository.save(job);

        return true;
    }

    @Override
    public Set<JobDto> getAppliedJobs(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        Set<Job> jobs = jobSeeker.getAppliedJobs();

        Set<JobDto> jobDtos = jobs.stream().map(job -> this.modelMapper.map(job, JobDto.class)).collect(Collectors.toSet());

        return jobDtos;
    }

    @Override
    public Set<JobDto> getShortListedJobs(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        Set<Job> jobs = jobSeeker.getShortlistedJobs();

        Set<JobDto> jobDtos = jobs.stream().map(job -> this.modelMapper.map(job, JobDto.class)).collect(Collectors.toSet());

        return jobDtos;
    }


}
