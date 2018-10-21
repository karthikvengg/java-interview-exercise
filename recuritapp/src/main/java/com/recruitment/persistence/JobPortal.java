package com.recruitment.persistence;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.recruitment.exception.ApplicationException;
import com.recruitment.model.JobOffer;


@Component
public class JobPortal {

    private Set<JobOffer> jobOffers;

    @Autowired
    public JobPortal() {
        jobOffers = new HashSet<>();
    }

    public void add(JobOffer jobOffer) throws ApplicationException {
        checkDuplicate(jobOffer);
        jobOffers.add(jobOffer);
    }

    public void checkDuplicate(final JobOffer jobOffer) throws ApplicationException {
        if (jobOffers.contains(jobOffer)) {
            throw new ApplicationException("Duplicate Job offer", HttpStatus.CONFLICT);
        }
    }

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public JobOffer getJobOffer(final String jobTitle) throws ApplicationException {

        Optional<JobOffer> optionalJobOffer = jobOffers.stream()
                .filter(jobOffer -> jobOffer.getJobTitle().equals(jobTitle))
                .findFirst();
        if(optionalJobOffer.isPresent())
            return optionalJobOffer.get();
        throw new ApplicationException("Job Offer doesn't exist", HttpStatus.NOT_FOUND);
    }
}
