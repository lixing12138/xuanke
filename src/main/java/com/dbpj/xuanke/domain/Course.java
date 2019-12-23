package com.dbpj.xuanke.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "c_id")
    private String cid;
    @Column(name = "c_title")
    private String ctitle;
    @Column(name = "c_dept_name")
    private String 	cdeptname;
    @Column(name = "c_credit")
    private Integer ccredit;
    @Column(name = "create_time",insertable = false)
    private Date createtime;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public Course(String cid, String ctitle, String cdeptname, Integer ccredit) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cdeptname = cdeptname;
        this.ccredit = ccredit;
    }

    public Integer getCcredit() {
        return ccredit;
    }

    public void setCcredit(Integer ccredit) {
        this.ccredit = ccredit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cid='" + cid + '\'' +
                ", ctitle='" + ctitle + '\'' +
                ", cdeptname='" + cdeptname + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCdeptname() {
        return cdeptname;
    }

    public void setCdeptname(String cdeptname) {
        this.cdeptname = cdeptname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Course(String cid, String ctitle, String cdeptname, Date createtime) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cdeptname = cdeptname;
        this.createtime = createtime;
    }
}
