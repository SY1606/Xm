package com.example.tt_xm.data.bean;

public class PayOrder {
    private String status;
    private String message;
    private final String SUCCESS_STATUS="0000";

    public boolean isSuccess(){
        return status.equals(SUCCESS_STATUS);
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
