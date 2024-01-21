package com.project.payloads;


import com.project.models.JobSeekerSocialNetwork;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobSeekerDto {

    private Integer jsId;

    private String name;

    private String jobTitle;

    private String currentSalary;

    private String expectedSalary;

    private Integer experience;

    private Integer age;

    private String educationLevel;

    private String languages;

    private String jobCategory;

    private String description;

    private String country;

    private String city;

    private JobSeekerSocialNetwork jobSeekerSocialNetwork;

}
