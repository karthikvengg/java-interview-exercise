package com.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Candidate {

    private String name;
    private String email;

    @JsonIgnore
    private JobApplication jobApplication;

    public Candidate(final JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /*
     *  Notify Status Change
     */
    void update() {
        System.out.println("Notification updated to - " + jobApplication.getApplicationStatus());
    }
}
