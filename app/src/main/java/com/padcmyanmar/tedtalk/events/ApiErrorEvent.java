package com.padcmyanmar.tedtalk.events;

public class ApiErrorEvent {
    private String errorMessage;

    public ApiErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
