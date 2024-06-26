package com.project.services.impl;

import com.project.Repositories.CompanyRepository;
import com.project.Repositories.JobSeekerRepository;
import com.project.Repositories.UserRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Company;
import com.project.models.JobSeeker;
import com.project.models.User;
import com.project.payloads.CompanyDto;
import com.project.payloads.JobSeekerAddressDto;
import com.project.payloads.JobSeekerDto;
import com.project.services.JobSeekerService;
import com.project.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public JobSeekerDto getJobSeekerById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));
        return this.modelMapper.map(jobSeeker, JobSeekerDto.class);
    }



    @Override
    public JobSeekerDto updateJobSeeker(JobSeekerDto jobSeekerDto, Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        JobSeeker jobSeeker = this.jobSeekerRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        jobSeeker.setName(jobSeekerDto.getName());
        jobSeeker.setJobTitle(jobSeekerDto.getJobTitle());
        jobSeeker.setCurrentSalary(jobSeekerDto.getCurrentSalary());
        jobSeeker.setExpectedSalary(jobSeekerDto.getExpectedSalary());
        jobSeeker.setExperience(jobSeekerDto.getExperience());
        jobSeeker.setAge(jobSeekerDto.getAge());
        jobSeeker.setEducationLevel(jobSeekerDto.getEducationLevel());
        jobSeeker.setLanguages(jobSeekerDto.getLanguages());
        jobSeeker.setJobCategory(jobSeekerDto.getJobCategory());
        jobSeeker.setDescription(jobSeekerDto.getDescription());
        jobSeeker.setCountry(jobSeekerDto.getCountry());
        jobSeeker.setCity(jobSeekerDto.getCity());
        jobSeeker.setJobSeekerSocialNetwork(jobSeekerDto.getJobSeekerSocialNetwork());

        JobSeeker updatedJobSeeker = this.jobSeekerRepository.save(jobSeeker);

        return modelMapper.map(updatedJobSeeker, JobSeekerDto.class);

    }

    @Override
    public void deleteJobSeeker(Integer jsId) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        this.jobSeekerRepository.delete(jobSeeker);

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

    @Override
    public JobSeekerDto getJobSeekerByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        JobSeeker jobSeeker = jobSeekerRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

        JobSeekerDto jobSeekerDto = this.modelMapper.map(jobSeeker, JobSeekerDto.class);

        return jobSeekerDto;

    }

    @Override
    public boolean updateJobSeekerAddress(Integer jsId, JobSeekerAddressDto jobSeekerAddressDto) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));
        jobSeeker.setCity(jobSeekerAddressDto.getCity());
        jobSeeker.setCountry(jobSeekerAddressDto.getCountry());

        this.jobSeekerRepository.save(jobSeeker);
        return true;

    }

    @Override
    public JobSeekerAddressDto getJobSeekerAddress(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));
        return this.modelMapper.map(jobSeeker, JobSeekerAddressDto.class);
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companyDtos = companies.stream().map(company -> modelMapper.map(company, CompanyDto.class)).toList();

        return companyDtos;
    }

    @Override
    public CompanyDto getCompanyById(Integer companyId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        return modelMapper.map(company, CompanyDto.class);

    }


}
