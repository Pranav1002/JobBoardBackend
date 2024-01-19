package com.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_seeker_education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "jsId")
    private JobSeeker jobSeeker;

    private String degree;

    private String major;

    private String startYear;

    private String lastYear;

    private String currentYear;

    private String uniName;

}
