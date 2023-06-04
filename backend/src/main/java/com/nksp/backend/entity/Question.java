package com.nksp.backend.entity;

import lombok.Data;

@Data
public class Question {
    int qid;
    int qtype;
    int qdiffculty;
    String qstem;
    int qnum;
    int qbelong;
}
