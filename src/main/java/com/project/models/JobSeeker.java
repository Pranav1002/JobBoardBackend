package com.project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jsId;

    private String name;

    private String jobTitle;

    private String currentSalary;

    private String expectedSalary;

    private Integer experience;

    private Integer age;

    private String educationLevel;  // Certificate

    private String languages;

    private String jobCategory;

    private String description;

    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private JobSeekerSocialNetwork jobSeekerSocialNetwork;

    private String country;

    private String city;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JSEducation> jsEducations;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JSExperience> jsExperience;
}
