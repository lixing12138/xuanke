package com.dbpj.xuanke.response;

public class AdminResponse {
    private Boolean result;
    private String type;
    private String message;

    public AdminResponse() {
    }

    public AdminResponse(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public AdminResponse(Boolean result, String type, String message) {
        this.result = result;
        this.type = type;
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
