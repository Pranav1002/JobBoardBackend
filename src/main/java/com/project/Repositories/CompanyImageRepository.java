package com.project.Repositories;

import com.project.models.CompanyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyImageRepository extends JpaRepository<CompanyImage, Integer> {

    Optional<CompanyImage> findByFileName(String fileName);

}
