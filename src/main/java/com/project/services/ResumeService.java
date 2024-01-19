package com.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ResumeService {

    public String uploadResume(MultipartFile file) throws IOException;

    public byte[] downloadResume(String fileName) throws IOException;

}
