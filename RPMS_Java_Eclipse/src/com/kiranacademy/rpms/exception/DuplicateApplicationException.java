package com.kiranacademy.rpms.exception;

public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException() {
        super("Candidate has already applied for this job");
    }

    public DuplicateApplicationException(String message) {
        super(message);
    }
}
