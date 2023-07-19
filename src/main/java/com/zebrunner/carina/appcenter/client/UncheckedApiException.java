package com.zebrunner.carina.appcenter.client;

public class UncheckedApiException extends RuntimeException {

    public UncheckedApiException(String message, ApiException apiException) {
        super(message, apiException);
    }
}
