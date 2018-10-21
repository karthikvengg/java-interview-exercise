package com.recruitment.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.recruitment.exception.ApplicationException;
import com.recruitment.model.ApplicationStatus;
import com.recruitment.model.JobApplication;
import com.recruitment.model.JobOffer;
import com.recruitment.persistence.JobPortal;
import com.recruitment.util.Validator;

@Service
public class JobApplicationService {

    @Autowired
    private JobPortal jobPortal;

    public JobApplication apply(final String jobTitle, final String candidateEmail, final String resumeText)
            throws ApplicationException {
        JobOffer jobOffer = jobPortal.getJobOffer(jobTitle);
        boolean isValid = Validator.validateEmail(candidateEmail);

        if (!isValid) {
            throw new ApplicationException("Invalid Email Id", HttpStatus.BAD_REQUEST);
        }

        //can add additional validation for resumeText
        if (resumeText == null || resumeText.isEmpty()) {
            throw new ApplicationException("Resume content is empty", HttpStatus.BAD_REQUEST);
        }

        JobApplication jobApplication = new JobApplication(jobOffer, candidateEmail, resumeText);
        jobOffer.add(jobApplication);

        return jobApplication;

    }

    public List<JobApplication> getJobApplication(final String jobTitle) throws ApplicationException {
        JobOffer jobOffer = jobPortal.getJobOffer(jobTitle);
        return jobOffer.getJobApplications();
    }

    public JobApplication getJobApplication(final String jobTitle, final String candidateEmail)
            throws ApplicationException {
        List<JobApplication> jobApplications = getJobApplication(jobTitle);
        Optional<JobApplication> optionalJobApplication = jobApplications.stream()
                .filter(jobApplication -> jobApplication.getCandidateEmail().equals(candidateEmail))
                .findFirst();
        if (optionalJobApplication.isPresent()) {
            return optionalJobApplication.get();
        }
        throw new ApplicationException("Job Application not found ", HttpStatus.NOT_FOUND);
    }

    public Integer getJobApplicationCount(final String jobTitle) throws ApplicationException {
        return getJobApplication(jobTitle).size();
    }

    public JobApplication changeStatus(final String jobTitle, final String candidateEmail, int status)
            throws ApplicationException {
        JobApplication jobApplication = getJobApplication(jobTitle, candidateEmail);
        ApplicationStatus[] values = ApplicationStatus.values();

        if(values.length >= status) {
            throw new ApplicationException("Invalid Status", HttpStatus.BAD_REQUEST);
        }

        ApplicationStatus applicationStatus = values[status];
        jobApplication.setApplicationStatus(applicationStatus);
        return jobApplication;
    }
}
