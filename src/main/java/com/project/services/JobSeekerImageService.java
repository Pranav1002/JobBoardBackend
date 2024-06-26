package com.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface JobSeekerImageService {

    public Map uploadImage(MultipartFile file, Integer userId) throws IOException;

    public String changeImage(MultipartFile file, Integer userId) throws IOException;

    public String downloadImage(Integer jsId) throws IOException;

}
