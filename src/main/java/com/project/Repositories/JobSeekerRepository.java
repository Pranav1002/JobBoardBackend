package com.project.Repositories;

import com.project.models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {

    Optional<JobSeeker> findByJsId(Integer jsId);

    //I have to write the query here.

}
