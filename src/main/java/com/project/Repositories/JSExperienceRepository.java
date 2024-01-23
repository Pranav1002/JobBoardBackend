package com.project.Repositories;

import com.project.models.JSExperience;
import com.project.models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JSExperienceRepository extends JpaRepository<JSExperience, Integer> {

    Optional<List<JSExperience>> findByJobSeeker(JobSeeker jobSeeker);

    Optional<JSExperience> findByExpId(Integer expId);
}
