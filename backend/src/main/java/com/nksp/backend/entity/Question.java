package com.nksp.backend.entity;

import lombok.Data;

@Data
public class Question {
    int qid;
    int qtype;
    int qdifficulty;
    String qstem;
    int qnum;
    int qbelong;

    public void setQuestion(int qtype, String qstem, int qnum, int qbelong){
        this.qtype = qtype;
        this.qstem = qstem;
        this.qnum = qnum;
        this.qbelong = qbelong;
    }
}
