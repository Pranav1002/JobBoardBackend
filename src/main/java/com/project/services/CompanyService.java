package com.project.services;

import com.project.payloads.CompanyDto;

public interface CompanyService {

    public boolean addCompany(CompanyDto companyDto);

    public CompanyDto getCompanyById(Integer companyId);

    public boolean updateCompany(CompanyDto companyDto, Integer companyId);

    public boolean deleteCompany(Integer companyId);

}
