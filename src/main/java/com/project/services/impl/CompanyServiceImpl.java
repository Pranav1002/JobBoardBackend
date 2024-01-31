package com.project.services.impl;

import com.project.Repositories.CompanyRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Company;
import com.project.payloads.CompanyDto;
import com.project.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

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
    public boolean updateCompany(CompanyDto companyDto, Integer companyId) {

        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        company.setName(companyDto.getName());
        company.setWebsite(companyDto.getWebsite());
        company.setEstablish(companyDto.getEstablish());
        company.setTeamSize(companyDto.getTeamSize());
        company.setCity(companyDto.getCity());
        company.setCountry(companyDto.getCountry());
        company.setAddress(companyDto.getAddress());
        company.setDescription(companyDto.getDescription());

        this.companyRepository.save(company);

        return true;
    }

    @Override
    public boolean deleteCompany(Integer companyId) {

        Company company = this.companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        this.companyRepository.delete(company);

        return true;
    }
}
