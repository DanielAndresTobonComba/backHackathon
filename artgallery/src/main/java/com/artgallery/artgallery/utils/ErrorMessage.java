package com.artgallery.artgallery.utils;

public class ErrorMessage extends RuntimeException {
    public ErrorMessage(String message) {
        super(message);
    }
}