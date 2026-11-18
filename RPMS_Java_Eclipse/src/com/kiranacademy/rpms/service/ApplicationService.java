package com.kiranacademy.rpms.service;

import java.util.List;
import java.util.UUID;

import com.kiranacademy.rpms.dao.ApplicationDAO;
import com.kiranacademy.rpms.dao.CandidateDAO;
import com.kiranacademy.rpms.dao.JobDAO;
import com.kiranacademy.rpms.exception.CandidateNotEligibleException;
import com.kiranacademy.rpms.exception.CandidateNotFoundException;
import com.kiranacademy.rpms.exception.DuplicateApplicationException;
import com.kiranacademy.rpms.exception.JobNotFoundException;
import com.kiranacademy.rpms.model.Application;
import com.kiranacademy.rpms.model.Candidate;
import com.kiranacademy.rpms.model.Job;

public class ApplicationService {

    private final CandidateDAO candidateDAO;
    private final JobDAO jobDAO;
    private final ApplicationDAO applicationDAO;
    private final EligibilityService eligibilityService;

    public ApplicationService(CandidateDAO candidateDAO, JobDAO jobDAO,
                              ApplicationDAO applicationDAO,
                              EligibilityService eligibilityService) {
        this.candidateDAO = candidateDAO;
        this.jobDAO = jobDAO;
        this.applicationDAO = applicationDAO;
        this.eligibilityService = eligibilityService;
    }

    public int applyForJob(int candidateId, int jobId) {
        Candidate candidate = candidateDAO.findById(candidateId);
        if (candidate == null)
            throw new CandidateNotFoundException();

        Job job = jobDAO.findById(jobId);
        if (job == null)
            throw new JobNotFoundException();

        if (applicationDAO.exists(candidateId, jobId))
            throw new DuplicateApplicationException();

        List<String> reasons = eligibilityService.getFailureReasons(candidate, job);
        if (!reasons.isEmpty())
            throw new CandidateNotEligibleException(String.join("; ", reasons));

        Application a = new Application();
        a.setApplicationCode("APP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        a.setCandidateId(candidateId);
        a.setJobId(jobId);
        a.setEligibilityStatus("ELIGIBLE");
        a.setEligibilityReason("Meets eligibility criteria");
        a.setApplicationStatus("APPLIED");

        return applicationDAO.save(a);
    }
}
