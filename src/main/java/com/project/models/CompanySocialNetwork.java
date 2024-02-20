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
public class CompanySocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer snId;

    @OneToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    private String twitter;

    private String LinkedIn;
}
