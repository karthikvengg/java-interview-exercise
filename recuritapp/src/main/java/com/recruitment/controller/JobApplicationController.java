package com.recruitment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.recruitment.exception.ApplicationException;
import com.recruitment.model.ApplicationStatus;
import com.recruitment.model.JobApplication;
import com.recruitment.model.dto.JobApplicatonDto;
import com.recruitment.service.JobApplicationService;


@RestController
@RequestMapping(value = "/jobapplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST,
            value = "/apply")
    public ResponseEntity<JobApplication> apply(@RequestBody JobApplicatonDto jobApplicatonDto)
            throws ApplicationException {
        JobApplication jobApplication = jobApplicationService.apply(jobApplicatonDto.getJobTitle(),
                jobApplicatonDto.getCandidateEmail(),
                jobApplicatonDto.getResumeText());
        return new ResponseEntity<>(jobApplication, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{title}", method = RequestMethod.GET)
    public ResponseEntity<List<JobApplication>> getJobApplication(@PathVariable("title") String jobTitle)
            throws ApplicationException {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplication(jobTitle);
        return new ResponseEntity<>(jobApplications, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{title}/{email}", method = RequestMethod.GET)
    public ResponseEntity<JobApplication> getJobApplication(@PathVariable("title") String jobTitle,
            @PathVariable("email") String candidateEmail)
            throws ApplicationException {
        JobApplication jobApplication = jobApplicationService.getJobApplication(jobTitle, candidateEmail);
        return new ResponseEntity<>(jobApplication, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{title}/{email}/status", method = RequestMethod.GET)
    public ResponseEntity<ApplicationStatus> getJobApplicationStatus(@PathVariable("email") String candidateEmail,
            @PathVariable("title") String jobTitle)
            throws ApplicationException {
        JobApplication jobApplication = jobApplicationService.getJobApplication(jobTitle, candidateEmail);
        return new ResponseEntity<>(jobApplication.getApplicationStatus(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{title}/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> getJobApplicationCount(@PathVariable("title") String jobTitle)
            throws ApplicationException {
        Integer jobApplicationCount = jobApplicationService.getJobApplicationCount(jobTitle);
        return new ResponseEntity<>(jobApplicationCount, HttpStatus.OK);
    }

    // we can restrict this api only to admin
    @RequestMapping(value = "/update/{title}/{email}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<JobApplication> changeApplicationStatus(@PathVariable("email") String candidateEmail,
            @PathVariable("title") String jobTitle, @PathVariable("status") int status) throws ApplicationException {
        JobApplication jobApplication = jobApplicationService.changeStatus(jobTitle, candidateEmail, status);
        return new ResponseEntity<>(jobApplication, HttpStatus.OK);
    }
}
