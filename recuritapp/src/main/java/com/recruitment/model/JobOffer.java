package com.recruitment.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class JobOffer {

    private String jobTitle; // (unique)
    private String jobDescription;
    private Date startDate;

    @JsonIgnore
    private List<JobApplication> jobApplications;

    public JobOffer(final String jobTitle, final String jobDescription) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;

        // job posted date will be creation date
        this.startDate = new Date();

        // to avoid race condition in multi-threaded scenario
        this.jobApplications = new CopyOnWriteArrayList<>();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!JobOffer.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final JobOffer other = (JobOffer) obj;

        return (this.jobTitle == null) ? (other.jobTitle == null) : this.jobTitle.equals(other.jobTitle);
    }

    @Override
    public int hashCode() {
        return jobTitle.hashCode();
    }

    public void add(final JobApplication jobApplication) {
        jobApplications.add(jobApplication);
    }
}
