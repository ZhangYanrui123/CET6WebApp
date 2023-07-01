package com.nksp.backend.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ExamInfo {
    int eid;
    String cname;
    Date ebegin;
    Date eend;
    int esubject;

    public void setInfo(int eid, String cname, Date ebegin, Date eend, int esubject){
        this.eid = eid;
        this.cname = cname;
        this.ebegin = ebegin;
        this.eend = eend;
        this.esubject = esubject;
    }
}
