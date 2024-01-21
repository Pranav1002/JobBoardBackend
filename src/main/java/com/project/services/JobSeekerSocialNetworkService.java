package com.project.services;

import com.project.models.JobSeekerSocialNetwork;

public interface JobSeekerSocialNetworkService {

    public void addSocialNetwork(JobSeekerSocialNetwork sn, Integer jsId);

    public JobSeekerSocialNetwork getSocialNetworkById(Integer jsId);

    public void updateSocialNetwork(JobSeekerSocialNetwork sn, Integer jsId);

}
