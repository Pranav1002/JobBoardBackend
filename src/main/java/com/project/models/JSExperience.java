package com.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JSExperience {

    @Id
    private Integer expId;

    private String jobRole;

    private String startYear;

    private String endYear;

    private String organization;

    private String description;

    @ManyToOne
    @JoinColumn(name = "js_id")
    @JsonBackReference
    private JobSeeker jobSeeker;

}
