package com.recruitment.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.recruitment.exception.ApplicationException;
import com.recruitment.model.JobOffer;
import com.recruitment.persistence.JobPortal;


@Service
public class JobOfferService {

    @Autowired
    private JobPortal jobPortal;

    public JobOffer create(final String jobTitle, final String jobDescription) throws ApplicationException {
        JobOffer jobOffer = new JobOffer(jobTitle, jobDescription);
        jobPortal.add(jobOffer);
        return jobOffer;

    }

    public Set<JobOffer> getAllJobOffers() throws ApplicationException {
        Set<JobOffer> jobOffers = jobPortal.getJobOffers();
        if (jobOffers.isEmpty()) {
            throw new ApplicationException("No Job offers are posted currently", HttpStatus.NOT_FOUND);
        }
        return jobOffers;
    }

    public JobOffer getJobOffer(final String jobTitle) throws ApplicationException {
        return jobPortal.getJobOffer(jobTitle);
    }
}
