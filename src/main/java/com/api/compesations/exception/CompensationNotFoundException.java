package com.api.compesations.exception;

public class CompensationNotFoundException extends RuntimeException {
    public CompensationNotFoundException(Long id) {
        super("Compensation with id " + id + " was not found");
    }
}