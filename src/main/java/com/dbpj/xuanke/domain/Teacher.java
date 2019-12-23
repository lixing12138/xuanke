package com.dbpj.xuanke.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "t_id")
    private String tid;
    @Column(name = "t_name")
    private String tname;
    @Column(name = "t_dept_name")
    private String tdeptname;
    @Column(name = "t_status", insertable = false)
    private Integer tstatus;
    @Column(name = "create_time", insertable = false)
    private Date createtime;

    public Teacher(String tid, String tname, String tdeptname) {
        this.tid = tid;
        this.tname = tname;
        this.tdeptname = tdeptname;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                ", tdeptname='" + tdeptname + '\'' +
                ", tstatus=" + tstatus +
                ", createtime=" + createtime +
                '}';
    }

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTdeptname() {
        return tdeptname;
    }

    public void setTdeptname(String tdeptname) {
        this.tdeptname = tdeptname;
    }

    public Integer getTstatus() {
        return tstatus;
    }

    public void setTstatus(Integer tstatus) {
        this.tstatus = tstatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
