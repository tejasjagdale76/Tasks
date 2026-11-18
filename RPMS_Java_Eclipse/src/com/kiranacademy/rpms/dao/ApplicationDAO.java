package com.kiranacademy.rpms.dao;

import java.util.List;
import com.kiranacademy.rpms.model.Application;

public interface ApplicationDAO {
    int save(Application application);
    boolean exists(int candidateId, int jobId);
    Application findById(int applicationId);
    List<Application> findByCandidate(int candidateId);
    boolean updateStatus(int applicationId, String newStatus);
}
