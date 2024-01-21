package com.project.services.impl;

import com.project.Repositories.JobSeekerRepository;
import com.project.Repositories.JobSeekerSocialNetworkRepository;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.JobSeeker;
import com.project.models.JobSeekerSocialNetwork;
import com.project.services.JobSeekerSocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSeekerSocialNetworkImpl implements JobSeekerSocialNetworkService {

    @Autowired
    private JobSeekerSocialNetworkRepository jobSeekerSocialNetworkRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public void addSocialNetwork(JobSeekerSocialNetwork sn, Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        sn.setJobSeeker(jobSeeker);

        this.jobSeekerSocialNetworkRepository.save(sn);
    }

    @Override
    public JobSeekerSocialNetwork getSocialNetworkById(Integer jsId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        JobSeekerSocialNetwork sn = this.jobSeekerSocialNetworkRepository.findByJobSeeker(jobSeeker).orElseThrow(() -> new ResourceNotFoundException("JobSeekerSocialNetwork", " Id ", jsId));

        return sn;

    }

    @Override
    public void updateSocialNetwork(JobSeekerSocialNetwork sn, Integer jsId) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jsId).orElseThrow(() -> new ResourceNotFoundException("JobSeeker", " Id ", jsId));

        JobSeekerSocialNetwork sn1 = this.jobSeekerSocialNetworkRepository.findByJobSeeker(jobSeeker).orElseThrow(() -> new ResourceNotFoundException("JobSeekerSocialNetwork", " Id ", jsId));

        sn1.setTwitter(sn.getTwitter());
        sn1.setLinkedIn(sn.getLinkedIn());

        this.jobSeekerSocialNetworkRepository.save(sn1);

    }
}
