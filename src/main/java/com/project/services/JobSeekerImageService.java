package com.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface JobSeekerImageService {

    public String uploadImage(MultipartFile file, Integer jsId) throws IOException;

    public String changeImage(MultipartFile file, Integer jsId) throws IOException;

    public byte[] downloadImage(String fileName) throws IOException;

}
