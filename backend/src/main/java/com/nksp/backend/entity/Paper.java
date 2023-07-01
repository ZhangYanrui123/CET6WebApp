package com.nksp.backend.entity;

import lombok.Data;

@Data
public class Paper {
    int pid;
    String pquestions;
    String pscores;

    public Paper(int pid, String pquestions, String pscores) {
        this.pid = pid;
        this.pquestions = pquestions;
        this.pscores = pscores;
    }

    public Paper() {
    }
}
