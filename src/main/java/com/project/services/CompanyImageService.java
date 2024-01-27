package com.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CompanyImageService {

    public String uploadImage(MultipartFile file, Integer companyId) throws IOException;

    public String changeImage(MultipartFile file, Integer companyId) throws IOException;

    public byte[] downloadImage(String fileName) throws IOException;

}
