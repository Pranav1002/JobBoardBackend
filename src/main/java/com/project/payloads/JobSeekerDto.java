package com.project.payloads;


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

    private String currentLocation;

    private String skillset;

}
