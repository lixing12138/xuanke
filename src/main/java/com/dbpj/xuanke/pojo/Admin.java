package com.dbpj.xuanke.pojo;

public class Admin {
    private Long id;

    private String a_id;

    private String a_password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id == null ? null : a_id.trim();
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password == null ? null : a_password.trim();
    }
}