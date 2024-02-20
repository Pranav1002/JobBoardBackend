package com.project.Repositories;

import com.project.models.Company;
import com.project.models.CompanySocialNetwork;
import com.project.models.JobSeeker;
import com.project.models.JobSeekerSocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanySocialNetworkRepository extends JpaRepository<CompanySocialNetwork, Integer> {

    public Optional<CompanySocialNetwork> findByCompany(Company company);

}
