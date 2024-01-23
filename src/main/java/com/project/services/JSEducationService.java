package com.project.services;

import com.project.models.JSEducation;

import java.util.List;

public interface JSEducationService {

    public void addEducation(JSEducation education, Integer jsId);

    public List<JSEducation> getEducationById(Integer jsId);

    public List<JSEducation> getEducations();

    public JSEducation updateEducation(JSEducation education);

    public void deleteAllEducation(Integer jsId);

    public void deleteEducation(Integer eduId);

}
