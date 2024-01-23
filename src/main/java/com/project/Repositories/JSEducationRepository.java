package com.project.Repositories;

import com.project.models.JSEducation;
import com.project.models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JSEducationRepository extends JpaRepository<JSEducation, Integer> {

    Optional<List<JSEducation>> findByJobSeeker(JobSeeker jobSeeker);

    Optional<JSEducation> findByEduId(Integer eduId);
}
