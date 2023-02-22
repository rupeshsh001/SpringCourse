package com.testspring.spring.errorHandLing;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timeStamp;


    public ErrorResponse() {
    System.out.println("idhar bhi aa gya");
    }

    public ErrorResponse(int statusCode, String message, long timeStamp) {
        System.out.println("phuch gya");
        this.statusCode = statusCode;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

}

   
