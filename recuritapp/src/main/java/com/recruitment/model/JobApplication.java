package com.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class JobApplication {

    private JobOffer jobOffer;

    private Candidate candidate;  // unique per Offer

    private String resumeText;

    private ApplicationStatus applicationStatus;

    public JobApplication(JobOffer jobOffer, String candidateEmail, String resumeText) {
        this.jobOffer = jobOffer;
        this.resumeText = resumeText;
        this.applicationStatus = ApplicationStatus.APPLIED;

        /*
         * unique email can be handled by overriding equals and hashcode
         * Not implemented due to time constraints
         */
        setCandidateEmail(candidateEmail);
    }

    private void setCandidateEmail(final String candidateEmail) {
        candidate = new Candidate(this);
        candidate.setEmail(candidateEmail);
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    @JsonIgnore
    public String getCandidateEmail() {
        return candidate.getEmail();
    }

    public String getResumeText() {
        return resumeText;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(final ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
        notifyCandidate();
    }

    private void notifyCandidate() {
        candidate.update();
    }

}
