package com.kiranacademy.rpms.dao;

import java.util.List;
import com.kiranacademy.rpms.model.Job;

public interface JobDAO {
    Job findById(int jobId);
    List<Job> findOpenJobs();
}
