package com.recruitment.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.recruitment.exception.ApplicationException;
import com.recruitment.model.JobOffer;
import com.recruitment.service.JobOfferService;


@RestController
@RequestMapping(value = "/joboffer")
public class JobOfferController {

    @Autowired
    private JobOfferService jobOfferService;

    @RequestMapping(value = "/create/{title}", method = RequestMethod.POST)
    public ResponseEntity<JobOffer> create(@PathVariable("title") String jobTitle, @RequestBody String jobDescription)
            throws ApplicationException {
        JobOffer jobOffer = jobOfferService.create(jobTitle, jobDescription);
        return new ResponseEntity<>(jobOffer, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Set<JobOffer>> getAllJobOffers() throws ApplicationException {
        Set<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        return new ResponseEntity<>(jobOffers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{title}", method = RequestMethod.GET)
    public ResponseEntity<JobOffer> getAllJobOffers(@PathVariable("title") String jobTitle)
            throws ApplicationException {
        JobOffer jobOffer = jobOfferService.getJobOffer(jobTitle);
        return new ResponseEntity<>(jobOffer, HttpStatus.OK);
    }

}
