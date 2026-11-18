package com.kiranacademy.rpms.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException() {
        super("Candidate not found");
    }

    public CandidateNotFoundException(String message) {
        super(message);
    }
}
