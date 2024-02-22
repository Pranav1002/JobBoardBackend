package com.project.services.impl;

import com.project.Repositories.CompanyImageRepository;
import com.project.Repositories.CompanyRepository;
import com.project.Repositories.UserRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.*;
import com.project.services.CompanyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CompanyImageServiceImpl implements CompanyImageService {

    @Autowired
    private CompanyImageRepository companyImageRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    private final String FOLDER_PATH="C:\\Users\\PRANAV THAKKAR\\OneDrive\\Desktop\\project files\\company images\\";

    @Override
    public String uploadImage(MultipartFile file, Integer userId) throws IOException {
        String filePath =FOLDER_PATH+file.getOriginalFilename();

        CompanyImage image = companyImageRepository.save(CompanyImage.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));


        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        Company company = this.companyRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        company.setImage(image);

        companyRepository.save(company);

        if (image != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    @Override
    public String changeImage(MultipartFile file, Integer userId) throws IOException {
        // Check if the company exists
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        Company company = this.companyRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));


        CompanyImage oldImage = company.getImage();

        // Delete the current image if it exists
        CompanyImage currentImage = company.getImage();
        if (currentImage != null) {
            String currentImagePath = currentImage.getFilePath();
            File currentImageFile = new File(currentImagePath);
            if (currentImageFile.exists()) {
                currentImageFile.delete();
            }
            // Remove the reference to the old image in the company entity
            company.setImage(null);
        }

        this.companyImageRepository.delete(oldImage); //Remove data of old image from database.

        // Save the new image
        String newFilePath = FOLDER_PATH + file.getOriginalFilename();
        CompanyImage newImage = companyImageRepository.save(CompanyImage.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(newFilePath).build());

        file.transferTo(new File(newFilePath));

        // Update the company with the new image
        company.setImage(newImage);
        companyRepository.save(company);

        return "Image changed successfully: " + newFilePath;
    }


    @Override
    public byte[] downloadImage(String fileName) throws IOException {
        return new byte[0];
    }
}
