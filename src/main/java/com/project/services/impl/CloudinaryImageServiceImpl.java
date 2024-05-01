package com.project.services.impl;

import com.cloudinary.Cloudinary;
import com.project.services.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map upload(MultipartFile file) {

        try {
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
