package com.dbpj.xuanke.domain;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "u_id")
    private String uid;
    @Column(name = "u_password")
    private String upassword;
    @Column(name = "create_time", insertable = false)
    private Date createtime;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", upassword='" + upassword + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
