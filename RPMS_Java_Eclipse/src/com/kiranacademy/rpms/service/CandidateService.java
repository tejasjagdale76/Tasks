package com.kiranacademy.rpms.service;

import java.util.List;

import com.kiranacademy.rpms.dao.CandidateDAO;
import com.kiranacademy.rpms.exception.CandidateNotFoundException;
import com.kiranacademy.rpms.model.Candidate;
import com.kiranacademy.rpms.util.ValidationUtil;

public class CandidateService {

    private final CandidateDAO candidateDAO;

    public CandidateService(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
    }

    public int addCandidate(Candidate candidate) {
        if (!ValidationUtil.isValidEmail(candidate.getEmail()))
            throw new IllegalArgumentException("Invalid email");

        if (!ValidationUtil.isValidMobile(candidate.getMobile()))
            throw new IllegalArgumentException("Mobile must contain 10 digits");

        if (!ValidationUtil.isValidPercentage(candidate.getPercentage()))
            throw new IllegalArgumentException("Percentage must be 0-100");

        if (!ValidationUtil.isValidBacklogCount(candidate.getBacklogCount()))
            throw new IllegalArgumentException("Backlog count cannot be negative");

        if (candidateDAO.findByEmail(candidate.getEmail()) != null)
            throw new IllegalArgumentException("Duplicate email");

        if (candidateDAO.findByMobile(candidate.getMobile()) != null)
            throw new IllegalArgumentException("Duplicate mobile");

        return candidateDAO.save(candidate);
    }

    public Candidate getCandidate(int id) {
        Candidate c = candidateDAO.findById(id);
        if (c == null) throw new CandidateNotFoundException("Candidate ID " + id + " not found");
        return c;
    }

    public List<Candidate> getAllCandidates() {
        return candidateDAO.findAll();
    }

    public List<Candidate> searchByTechnology(String technology) {
        return candidateDAO.findByTechnology(technology);
    }
}
