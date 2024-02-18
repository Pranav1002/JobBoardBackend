package com.project.Repositories;

import com.project.models.JobSeeker;
import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {

    Optional<JobSeeker> findByJsId(Integer jsId);

    Optional<JobSeeker> findByUser(User user);

}
