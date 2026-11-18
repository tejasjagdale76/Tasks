package com.kiranacademy.rpms.exception;

public class CandidateNotEligibleException extends RuntimeException {
    public CandidateNotEligibleException() {
        super("Candidate is not eligible for this job");
    }

    public CandidateNotEligibleException(String message) {
        super(message);
    }
}
