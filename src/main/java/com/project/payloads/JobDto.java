package com.project.payloads;

import com.project.models.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    private String jobId;

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

    private Company company;

}
