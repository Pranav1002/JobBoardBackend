package com.project.services;

import com.project.payloads.CompanyAddressDto;
import com.project.payloads.CompanyDto;

public interface CompanyService {

    public boolean addCompany(CompanyDto companyDto);

    public CompanyDto getCompanyById(Integer companyId);

    public CompanyDto updateCompany(CompanyDto companyDto, Integer userId);

    public boolean deleteCompany(Integer companyId);

    public CompanyDto getCompanyByUserId(Integer userId);

    public boolean updateCompanyAddress(Integer companyId, CompanyAddressDto companyAddressDto);

    public CompanyAddressDto getCompanyAddress(Integer companyId);

}
