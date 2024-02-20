package com.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    private String email;

    private String phoneNumber;

    private String name;

    private String website;

    private String establish;

    private String teamSize;

    private String city;

    private String country;

    private String address;

    private String Description;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImage image;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Job> jobs;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private User user;

}
