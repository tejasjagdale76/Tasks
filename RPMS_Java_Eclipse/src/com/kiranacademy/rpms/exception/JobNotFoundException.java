package com.kiranacademy.rpms.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Job not found");
    }

    public JobNotFoundException(String message) {
        super(message);
    }
}
