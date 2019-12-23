package com.dbpj.xuanke.response;

public class UserResponse {
    private Boolean result;
    private String type;
    private String message;
    private String name;

    public UserResponse(Boolean result, String type, String message, String name) {
        this.result = result;
        this.type = type;
        this.message = message;
        this.name = name;
    }

    public UserResponse() {
    }

    public UserResponse(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public UserResponse(Boolean result, String type, String message) {
        this.result = result;
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
