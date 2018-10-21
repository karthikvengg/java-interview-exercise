package com.recruitment.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.recruitment.exception.ApplicationException;
import com.recruitment.model.JobOffer;
import com.recruitment.persistence.JobPortal;


@RunWith(MockitoJUnitRunner.class)
public class JobOfferServiceTest {

    /*
     *  Added only one unit test for the whole project due to time constraints
     */

    @InjectMocks
    private JobOfferService jobOfferService;

    @Mock
    private JobPortal jobPortal;

    @Before
    public void setUp() throws Exception {
        jobOfferService = new JobOfferService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws ApplicationException {
        String jobTitle = "softwareEngg2";
        String jobDescription = "eng2 level";

        Mockito.doNothing().when(jobPortal).add(Mockito.any());
        JobOffer jobOffer = jobOfferService.create(jobTitle, jobDescription);

        Assert.assertEquals(jobTitle, jobOffer.getJobTitle());
    }
}