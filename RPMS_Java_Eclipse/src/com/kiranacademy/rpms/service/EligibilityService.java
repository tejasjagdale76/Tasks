package com.kiranacademy.rpms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kiranacademy.rpms.model.Candidate;
import com.kiranacademy.rpms.model.Job;

public class EligibilityService {

    public List<String> getFailureReasons(Candidate c, Job j) {
        List<String> reasons = new ArrayList<>();

        if (!"ACTIVE".equalsIgnoreCase(c.getProfileStatus()))
            reasons.add("Candidate profile is not ACTIVE");

        if (!"OPEN".equalsIgnoreCase(j.getStatus()))
            reasons.add("Job is not OPEN");

        if (j.getApplicationDeadline() != null &&
                LocalDate.now().isAfter(j.getApplicationDeadline()))
            reasons.add("Application deadline has passed");

        if (c.getPercentage() < j.getMinPercentage())
            reasons.add("Percentage below minimum requirement");

        if (j.getMinPassingYear() > 0 && c.getPassingYear() < j.getMinPassingYear())
            reasons.add("Passing year is below allowed range");

        if (j.getMaxPassingYear() > 0 && c.getPassingYear() > j.getMaxPassingYear())
            reasons.add("Passing year is above allowed range");

        if (c.getBacklogCount() > j.getMaxBacklogs())
            reasons.add("Backlog count exceeds allowed limit");

        return reasons;
    }

    public boolean isEligible(Candidate c, Job j) {
        return getFailureReasons(c, j).isEmpty();
    }
}
