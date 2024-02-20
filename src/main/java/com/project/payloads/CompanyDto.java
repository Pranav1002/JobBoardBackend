package com.project.payloads;


import com.project.models.Company;
import com.project.models.CompanyImage;
import com.project.models.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

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

    private String description;

    private CompanyImage image;

}
