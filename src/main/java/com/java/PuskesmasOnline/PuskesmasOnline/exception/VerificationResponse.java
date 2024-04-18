package com.java.PuskesmasOnline.PuskesmasOnline.exception;

public class VerificationResponse {
    private boolean success;

    public VerificationResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}