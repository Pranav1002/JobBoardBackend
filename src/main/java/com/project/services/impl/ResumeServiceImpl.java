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

    private final String FOLDER_PATH="C:\\Users\\PRANAV THAKKAR\\OneDrive\\Desktop\\project files\\";


    @Override
    public Map uploadResume(MultipartFile file, Integer jsId) throws IOException {

        Resume resume = resumeRepository.save(Resume.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath("").build());


        JobSeeker jobSeeker = this.jobSeekerRepository.findByJsId(jsId).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        jobSeeker.setResume(resume);

        jobSeekerRepository.save(jobSeeker);

        try {
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    @Override
    public byte[] downloadResume(Integer jsId) throws IOException{

        JobSeeker jobSeeker = this.jobSeekerRepository.findByJsId(jsId).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        Resume resume = jobSeeker.getResume();

        String filePath = resume.getFilePath();

        byte[] file = Files.readAllBytes(new File(filePath).toPath());

        return file;

    }
}
