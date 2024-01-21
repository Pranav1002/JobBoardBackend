package com.project.Repositories;

import com.project.models.JobSeeker;
import com.project.models.JobSeekerSocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerSocialNetworkRepository extends JpaRepository<JobSeekerSocialNetwork, Integer> {

    public Optional<JobSeekerSocialNetwork> findByJobSeeker(JobSeeker jobSeeker);

}
