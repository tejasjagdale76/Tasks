package com.kiranacademy.rpms.dao;

import java.util.List;
import com.kiranacademy.rpms.model.Candidate;

public interface CandidateDAO {
    int save(Candidate candidate);
    boolean update(Candidate candidate);
    Candidate findById(int candidateId);
    Candidate findByEmail(String email);
    Candidate findByMobile(String mobile);
    List<Candidate> findAll();
    List<Candidate> findByTechnology(String technology);
    boolean deactivate(int candidateId);
}
