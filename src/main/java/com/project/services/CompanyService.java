package com.project.services;

import com.project.models.JSEducation;
import com.project.models.JSExperience;
import com.project.payloads.CompanyAddressDto;
import com.project.payloads.CompanyDto;
import com.project.payloads.JobDto;
import com.project.payloads.JobSeekerDto;

import java.util.List;

public interface CompanyService {

    public boolean addCompany(CompanyDto companyDto);

    public CompanyDto getCompanyById(Integer companyId);

    public CompanyDto updateCompany(CompanyDto companyDto, Integer userId);

    public boolean deleteCompany(Integer companyId);

    public CompanyDto getCompanyByUserId(Integer userId);

    public boolean updateCompanyAddress(Integer companyId, CompanyAddressDto companyAddressDto);

    public CompanyAddressDto getCompanyAddress(Integer companyId);

    public List<JobSeekerDto> getApplicants(Integer companyId);

    public List<JobSeekerDto> getAllJobSeekers();

    public JobSeekerDto getJobSeekerById(Integer jsId);

    public List<JSExperience> getExperienceById(Integer jsId);

    public List<JSEducation> getEducationById(Integer jsId);

}
