package com.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String jobTitle;

    private String jobDescription;

    private String jobType;

    private Integer experience;

    private String salary;

    private String gender;

    private String industry;

    private String qualification;

    private String deadline;

    private String city;

    private String country;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;


    @ManyToMany
    @JoinTable(
            name = "job_jobseeker",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "jobseeker_id")
    )
    private Set<JobSeeker> jobSeekers;

    @ManyToMany
    @JoinTable(
            name = "job_shortlisted_jobseeker",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "jobseeker_id")
    )
    private Set<JobSeeker> shortlistedJobSeekers;


}
