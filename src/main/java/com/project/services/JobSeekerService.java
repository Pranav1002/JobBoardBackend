package com.project.services;

import com.project.models.JobSeeker;
import com.project.payloads.CompanyAddressDto;
import com.project.payloads.CompanyDto;
import com.project.payloads.JobSeekerAddressDto;
import com.project.payloads.JobSeekerDto;

import java.util.List;

public interface JobSeekerService {

    public JobSeekerDto getJobSeekerById(Integer jsId);


    public JobSeekerDto updateJobSeeker(JobSeekerDto jobSeeker, Integer userId);

    public void deleteJobSeeker(Integer jsId);

    public void addJobSeeker(JobSeekerDto jobSeekerDto);

    public List<JobSeekerDto> getAllJobSeekers();

    public JobSeekerDto getJobSeekerByUserId(Integer userId);

    public boolean updateJobSeekerAddress(Integer jsId, JobSeekerAddressDto jobSeekerAddressDto);

    public JobSeekerAddressDto getJobSeekerAddress(Integer jsId);

    public List<CompanyDto> getAllCompanies();

    public CompanyDto getCompanyById(Integer companyId);

}
