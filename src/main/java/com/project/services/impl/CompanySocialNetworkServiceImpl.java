package com.project.services.impl;

import com.project.Repositories.CompanyRepository;
import com.project.Repositories.CompanySocialNetworkRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Company;
import com.project.models.CompanySocialNetwork;
import com.project.models.JobSeeker;
import com.project.models.JobSeekerSocialNetwork;
import com.project.services.CompanySocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanySocialNetworkServiceImpl implements CompanySocialNetworkService {

    @Autowired
    private CompanySocialNetworkRepository companySocialNetworkRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void addSocialNetwork(CompanySocialNetwork sn, Integer companyId) {

        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        sn.setCompany(company);

        this.companySocialNetworkRepository.save(sn);

    }

    @Override
    public CompanySocialNetwork getSocialNetworkById(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        CompanySocialNetwork sn = this.companySocialNetworkRepository.findByCompany(company).orElseThrow(() -> new ResourceNotFoundException("CompanySocialNetwork", " Id ", companyId));

        return sn;
    }

    @Override
    public void updateSocialNetwork(CompanySocialNetwork sn, Integer companyId) {

        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", " Id ", companyId));

        CompanySocialNetwork sn1 = this.companySocialNetworkRepository.findByCompany(company).orElseThrow(() -> new ResourceNotFoundException("JobSeekerSocialNetwork", " Id ", companyId));

        sn1.setTwitter(sn.getTwitter());
        sn1.setLinkedIn(sn.getLinkedIn());

        this.companySocialNetworkRepository.save(sn1);

    }
}
