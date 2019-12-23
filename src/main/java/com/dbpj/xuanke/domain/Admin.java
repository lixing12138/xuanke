package com.dbpj.xuanke.domain;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "a_id")
    private String aid;

    @Column(name = "a_password")
    private String apassword;

    public Admin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", aid='" + aid + '\'' +
                ", apassword='" + apassword + '\'' +
                '}';
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }
}
