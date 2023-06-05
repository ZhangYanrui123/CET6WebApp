package com.nksp.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Exam {
    int eid;
    int cid;
    Date ebegin;
    Date eend;
    int esubject;
    int estate;
    int pid;

    public void setInfo(Exam exam){
        this.eid = exam.eid;
        this.cid = exam.cid;
        this.ebegin = exam.ebegin;
        this.eend = exam.eend;
        this.esubject = exam.esubject;
        this.estate = exam.estate;
        this.pid = exam.pid;
    }
}
