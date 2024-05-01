package com.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ResumeService {

    public Map uploadResume(MultipartFile file, Integer jsId) throws IOException;

    public byte[] downloadResume(Integer jsId) throws IOException;

}
