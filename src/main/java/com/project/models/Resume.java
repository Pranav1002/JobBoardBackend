package com.project.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;

    private String fileName;
    private String fileType;
    private String filePath;

    @OneToOne
    @JoinColumn(name = "js_id")
    private JobSeeker jobSeeker;

}
