package com.project.services.impl;

import com.project.Repositories.*;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.*;
import com.project.payloads.CompanyAddressDto;
import com.project.payloads.CompanyDto;
import com.project.payloads.JobDto;
import com.project.payloads.JobSeekerDto;
import com.project.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JSEducationRepository jsEducationRepository;

    @Autowired
    private JSExperienceRepository jsExperienceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addCompany(CompanyDto companyDto) {

        Company company = this.modelMapper.map(companyDto, Company.class);

        companyRepository.save(company);

        return true;
    }

    @Override
    public CompanyDto getCompanyById(Integer companyId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));
        return this.modelMapper.map(company, CompanyDto.class);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        Company company = this.companyRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        company.setName(companyDto.getName());
        company.setWebsite(companyDto.getWebsite());
        company.setEstablish(companyDto.getEstablish());
        company.setTeamSize(companyDto.getTeamSize());
        company.setDescription(companyDto.getDescription());
        company.setEmail(companyDto.getEmail());
        company.setPhoneNumber(companyDto.getPhoneNumber());

        this.companyRepository.save(company);

        return this.modelMapper.map(company,CompanyDto.class);
    }

    @Override
    public boolean deleteCompany(Integer companyId) {

        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        this.companyRepository.delete(company);

        return true;
    }

    @Override
    public CompanyDto getCompanyByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        Company company = this.companyRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        return this.modelMapper.map(company, CompanyDto.class);
    }

    @Override
    public boolean updateCompanyAddress(Integer companyId, CompanyAddressDto companyAddressDto) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));
        company.setCountry(companyAddressDto.getCountry());
        company.setCity(companyAddressDto.getCity());
        company.setAddress(companyAddressDto.getAddress());

        this.companyRepository.save(company);

        return true;
    }

    @Override
    public CompanyAddressDto getCompanyAddress(Integer companyId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));
        return this.modelMapper.map(company, CompanyAddressDto.class);
    }

    @Override
    public List<JobSeekerDto> getApplicants(Integer companyId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        List<Job> jobs = jobRepository.findByCompany(company).orElseThrow(()-> new RuntimeException());

        List<JobSeekerDto> applicants= new ArrayList<>();

        for(Job job : jobs){
            Set<JobSeeker> jobSeekers = job.getJobSeekers();

            applicants.addAll(jobSeekers.stream().map(jobSeeker -> modelMapper.map(jobSeeker, JobSeekerDto.class)).toList());
        }

        return applicants;

    }

    @Override
    public List<JobSeekerDto> getAllJobSeekers() {

        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();

        List<JobSeekerDto> jobSeekerDtos = jobSeekers.stream().map(jobSeeker->modelMapper.map(jobSeeker, JobSeekerDto.class)).toList();

        return jobSeekerDtos;

    }

    @Override
    public JobSeekerDto getJobSeekerById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));
        return modelMapper.map(jobSeeker, JobSeekerDto.class);
    }

    @Override
    public List<JSExperience> getExperienceById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", "Id", jsId));

        List<JSExperience> experiences = this.jsExperienceRepository.findByJobSeeker(jobSeeker).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", "Id", jsId));

        return experiences;
    }

    @Override
    public List<JSEducation> getEducationById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        List<JSEducation> educations = this.jsEducationRepository.findByJobSeeker(jobSeeker).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        return educations;
    }

}
