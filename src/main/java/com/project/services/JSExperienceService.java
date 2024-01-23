package com.project.services;

import com.project.models.JSEducation;
import com.project.models.JSExperience;

import java.util.List;

public interface JSExperienceService {

    public void addExperience(JSExperience experience, Integer jsId);

    public List<JSExperience> getExperienceById(Integer jsId);

    public List<JSExperience> getExperiences();

    public JSExperience updateExperience(JSExperience experience);

    public void deleteAllExperience(Integer jsId);

    public void deleteOneExperience(Integer expId);
}
