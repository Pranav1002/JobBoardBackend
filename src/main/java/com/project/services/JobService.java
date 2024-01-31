package com.project.services;

import com.project.models.Job;
import com.project.payloads.JobDto;
import com.project.payloads.JobSeekerDto;

import java.util.List;
import java.util.Set;

public interface JobService {

    public boolean postJob(JobDto jobDto, Integer companyId);

    public List<JobDto> getJobByCompanyId(Integer companyId);

    public boolean updateJob(JobDto jobDto, Integer jobId, Integer companyId);

    public boolean deleteJob(Integer jobId, Integer companyId);

    public Set<JobSeekerDto> getJobApplicants(Integer jobId);

    public boolean shortlistApplicantByJob(Integer jobId, Integer jobSeekerId);

    public Set<JobSeekerDto> getShortlistedApplicantsByJob(Integer jobId);


    public List<JobDto> getAllJobs();

    public boolean applyJob(Integer jsId, Integer jobId);

    public Set<JobDto> getAppliedJobs(Integer jsId);

    public Set<JobDto> getShortListedJobs(Integer jsId);

}
