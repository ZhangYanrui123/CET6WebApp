package com.nksp.backend.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AddExam {
    int eid;
    String cname;
    Date ebegin;
    Date eend;
    int esubject;
    int estate;
    int pid;

}

