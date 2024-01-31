package com.project.Repositories;

import com.project.models.Company;
import com.project.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    Optional<List<Job>> findByCompany(Company company);

}
