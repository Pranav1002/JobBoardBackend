package com.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_seeker_experience")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "jsId")
    private JobSeeker jobSeeker;

    private String description;

    private String companyName;

    private String job_title;

    private String startYear;

    private String endYear;

}
