package ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException (String message) {
        super(message);
    }
}
