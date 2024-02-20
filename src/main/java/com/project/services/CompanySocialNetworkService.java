package com.project.services;

import com.project.models.CompanySocialNetwork;
import com.project.models.JobSeekerSocialNetwork;

public interface CompanySocialNetworkService {

    public void addSocialNetwork(CompanySocialNetwork sn, Integer companyId);

    public CompanySocialNetwork getSocialNetworkById(Integer companyId);

    public void updateSocialNetwork(CompanySocialNetwork sn, Integer companyId);

}
