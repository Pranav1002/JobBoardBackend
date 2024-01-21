package com.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerSocialNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer snId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "js_id")
    @JsonBackReference
    private JobSeeker jobSeeker;

    private String twitter;

    private String LinkedIn;

}
