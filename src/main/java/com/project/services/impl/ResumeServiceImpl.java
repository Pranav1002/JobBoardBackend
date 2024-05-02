package com.project.services.impl;

import com.cloudinary.Cloudinary;
import com.project.Repositories.JobSeekerRepository;
import com.project.Repositories.ResumeRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.JobSeeker;
import com.project.models.Resume;
import com.project.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private Cloudinary cloudinary;



    @Override
    public Map uploadResume(MultipartFile file, Integer jsId) throws IOException {



        try {
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

            String cloudinaryUrl = (String) data.get("secure_url");

            Resume resume = resumeRepository.save(Resume.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .filePath(cloudinaryUrl).build());


            JobSeeker jobSeeker = this.jobSeekerRepository.findByJsId(jsId).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

            jobSeeker.setResume(resume);

            jobSeekerRepository.save(jobSeeker);

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    @Override
    public String downloadResume(Integer jsId) throws IOException{

        JobSeeker jobSeeker = this.jobSeekerRepository.findByJsId(jsId).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        Resume resume = jobSeeker.getResume();

        String filePath = resume.getFilePath();

        return filePath;

    }
}
