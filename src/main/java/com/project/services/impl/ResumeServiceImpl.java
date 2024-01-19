package com.project.services.impl;

import com.project.Repositories.ResumeRepository;
import com.project.models.Resume;
import com.project.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    private final String FOLDER_PATH="C:\\Users\\PRANAV THAKKAR\\OneDrive\\Desktop\\SDP\\JobBoardBackend\\src\\main\\resources\\static\\";


    @Override
    public String uploadResume(MultipartFile file) throws IOException {
        String filePath =FOLDER_PATH+file.getOriginalFilename();

        Resume resume = resumeRepository.save(Resume.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (resume != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;

    }

    @Override
    public byte[] downloadResume(String fileName) throws IOException{

        Optional<Resume> resume = resumeRepository.findByFileName(fileName);

        String filePath = resume.get().getFilePath();

        byte[] file = Files.readAllBytes(new File(filePath).toPath());

        return file;

    }
}
