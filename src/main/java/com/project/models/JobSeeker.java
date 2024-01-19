package com.project.models;

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

    private String currentLocation;

    private String skillset;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Experience> experiences;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "resume_id")
    private Resume resume;


}
