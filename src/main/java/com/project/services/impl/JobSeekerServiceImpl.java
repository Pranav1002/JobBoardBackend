package com.project.services.impl;

import com.project.Repositories.JobSeekerImageRepository;
import com.project.Repositories.JobSeekerRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.JobSeeker;
import com.project.models.JobSeekerImage;
import com.project.services.JobSeekerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class JobSeekerServiceImpl implements JobSeekerImageService {
    @Autowired
    private JobSeekerImageRepository jobSeekerImageRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    private final String FOLDER_PATH="C:\\Users\\PRANAV THAKKAR\\OneDrive\\Desktop\\project files\\jobSeeker images\\";

    @Override
    public String uploadImage(MultipartFile file, Integer jsId) throws IOException {
        String filePath =FOLDER_PATH+file.getOriginalFilename();

        JobSeekerImage image = jobSeekerImageRepository.save(JobSeekerImage.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        JobSeeker jobSeeker = this.jobSeekerRepository.findById(jsId).orElseThrow(()->new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        jobSeeker.setImage(image);

        jobSeekerRepository.save(jobSeeker);

        if (image != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    @Override
    public String changeImage(MultipartFile file, Integer jsId) throws IOException {
        // Check if the jobSeeker exists
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", jsId));

        JobSeekerImage oldImage = jobSeeker.getImage();

        // Delete the current image if it exists
        JobSeekerImage currentImage = jobSeeker.getImage();
        if (currentImage != null) {
            String currentImagePath = currentImage.getFilePath();
            File currentImageFile = new File(currentImagePath);
            if (currentImageFile.exists()) {
                currentImageFile.delete();
            }
            // Remove the reference to the old image in the company entity
            jobSeeker.setImage(null);
        }

        this.jobSeekerImageRepository.delete(oldImage);

        // Save the new image
        String newFilePath = FOLDER_PATH + file.getOriginalFilename();
        JobSeekerImage newImage = jobSeekerImageRepository.save(JobSeekerImage.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(newFilePath).build());

        file.transferTo(new File(newFilePath));

        // Update the jobSeeker with the new image
        jobSeeker.setImage(newImage);
        jobSeekerRepository.save(jobSeeker);

        return "Image changed successfully: " + newFilePath;
    }


    @Override
    public byte[] downloadImage(String fileName) throws IOException {
        return new byte[0];
    }
}
