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
@NoArgsConstructor
@AllArgsConstructor
public class JSEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eduId;

    private String course;

    private String startYear;

    private String endYear;

    private String organization;

    private String description;

    @ManyToOne
    @JoinColumn(name = "js_d")
    @JsonBackReference
    private JobSeeker jobSeeker;

}
