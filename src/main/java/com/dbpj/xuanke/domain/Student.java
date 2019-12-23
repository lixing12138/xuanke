package com.dbpj.xuanke.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "s_id")
    private String 	sid;
    @Column(name = "s_name")
    private String 	sname;
    @Column(name = "s_dept_name")
    private String 	sdeptname;
    @Column(name = "s_class")
    private String sclass;
    @Column(name = "s_total_credit", insertable = false)
    private Long stotalcredit;
    @Column(name = "s_status", insertable = false)
    private Integer sstatus;
    @Column(name = "create_time", insertable = false)
    private Date createtime;

    public Student() {
    }

    public Student(String sid, String sname, String sdeptname, String sclass) {
        this.sid = sid;
        this.sname = sname;
        this.sdeptname = sdeptname;
        this.sclass = sclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", sdeptname='" + sdeptname + '\'' +
                ", sclass='" + sclass + '\'' +
                ", stotalcredit='" + stotalcredit + '\'' +
                ", sstatus='" + sstatus + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSdeptname() {
        return sdeptname;
    }

    public void setSdeptname(String sdeptname) {
        this.sdeptname = sdeptname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public Long getStotalcredit() {
        return stotalcredit;
    }

    public void setStotalcredit(Long stotalcredit) {
        this.stotalcredit = stotalcredit;
    }

    public Integer getSstatus() {
        return sstatus;
    }

    public void setSstatus(Integer sstatus) {
        this.sstatus = sstatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
