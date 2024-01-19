package com.project.Repositories;

import com.project.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    Optional<Resume> findByFileName(String fileName);
}
