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
}
