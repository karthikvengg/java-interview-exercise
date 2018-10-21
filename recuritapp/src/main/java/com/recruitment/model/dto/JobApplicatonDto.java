package com.recruitment.model.dto;

public class JobApplicatonDto {

    private String jobTitle;
    private String candidateEmail;
    private String resumeText;

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCandidateEmail(final String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public void setResumeText(final String resumeText) {
        this.resumeText = resumeText;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public String getResumeText() {
        return resumeText;
    }
}
