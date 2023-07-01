package com.nksp.backend.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ExamInfo {
    String cname;
    Date ebegin;
    Date eend;
    int esubject;

    public void setInfo(String cname, Date ebegin, Date eend, int esubject){
        this.cname = cname;
        this.ebegin = ebegin;
        this.eend = eend;
        this.esubject = esubject;
    }
}
